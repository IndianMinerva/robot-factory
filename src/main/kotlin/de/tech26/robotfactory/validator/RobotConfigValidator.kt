package de.tech26.robotfactory.validator

import de.tech26.robotfactory.exception.RobotConfigInvalidException
import de.tech26.robotfactory.model.OrderInput
import de.tech26.robotfactory.model.RobotComponent
import de.tech26.robotfactory.model.RobotComponentType

object RobotConfigValidator {
    fun validateRoboConfig(robotComponents: Map<String, RobotComponent>, orderInput: OrderInput) {
        val componentList: List<RobotComponent?> = orderInput.components.map { componentId -> robotComponents.get(componentId) }   // TODO: Handle invalid component Id
//        val componentConfigMap: Map<RobotComponentType?, List<RobotComponent?>> = componentList.groupBy { it?.type }
        val componentConfigMap: Set<RobotComponentType?> = componentList.map { it?.type }.toSet()

        if (componentConfigMap.size != componentList.size) {
            throw RobotConfigInvalidException("Robot-config is missing essential component(s) : " + robotComponents.minus(componentConfigMap))
        }
    }

    @Synchronized fun isInventoryAvailable(componentInventory: Map<String, Int>, orderInput: OrderInput): Boolean {
        val availability = orderInput.components
            .map { componentId -> componentInventory.getOrDefault(componentId, 0) }
            .filter{ it <= 0 }
        return availability.isEmpty();
    }
}