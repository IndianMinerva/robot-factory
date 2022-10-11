package de.tech26.robotfactory.service

import de.tech26.robotfactory.exception.InventoryNotAvailableException
import de.tech26.robotfactory.model.OrderInput
import de.tech26.robotfactory.model.OrderOutput
import de.tech26.robotfactory.model.Robot
import de.tech26.robotfactory.model.RobotComponent
import de.tech26.robotfactory.validator.RobotConfigValidator
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashSet

@Service
class RobotFactoryService(
    val robotComponents: Map<String, RobotComponent>,
    val componentInventory: MutableMap<String, Int>,
    val robots: HashSet<Robot>
    ) {
    fun createRobot(orderInput: OrderInput): OrderOutput {
        RobotConfigValidator.validateRoboConfig(robotComponents = robotComponents, orderInput = orderInput)
        return allocateComponents(orderInput)
    }

    private fun allocateComponents(orderInput: OrderInput): OrderOutput {
        RobotConfigValidator.validateRoboConfig(robotComponents, orderInput)

        synchronized(componentInventory) { //Begin transaction (pessimistic concurrency protocol)
            if (RobotConfigValidator.isInventoryAvailable(this.componentInventory, orderInput)) {
                orderInput.components.forEach { componentId ->
                    val count = componentInventory.getValue(componentId)
                    componentInventory[componentId] = count - 1
                }
                val robotId = UUID.randomUUID().toString();
                robots.add(Robot(robotId, orderInput.components))
                val componentPrices = orderInput.components.map { robotComponents.getValue(it).price }.sum()
                return OrderOutput(robotId, componentPrices)
            } else {
                throw InventoryNotAvailableException("One or more of the required components are not available in the inventory")
            }
        }
    }
}