package de.tech26.robotfactory.service

import de.tech26.robotfactory.config.DBConfig
import de.tech26.robotfactory.exception.InventoryNotAvailableException
import de.tech26.robotfactory.exception.RobotConfigInvalidException
import de.tech26.robotfactory.model.OrderInput
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RobotFactoryServiceTest {
    private val dbConfig: DBConfig = DBConfig()
    private val robotFactoryService: RobotFactoryService = RobotFactoryService(dbConfig.getRobotComponents(), dbConfig.getRobotComponentsAvailability(), dbConfig.getRobots())

    @Test
    fun `should throw InventoryNotAvailableException`() {
        assertThrows<InventoryNotAvailableException>("should throw InventoryNotAvailableException") {
            robotFactoryService.createRobot(OrderInput(components = arrayOf("C", "D", "F", "I")))
        }
    }

    @Test
    fun `should throw RobotConfigInvalidException`() {
        assertThrows<RobotConfigInvalidException>("should throw RobotConfigInvalidException") {
            robotFactoryService.createRobot(OrderInput(components = arrayOf("A", "C", "D", "F", "I")))
        }
    }

    @Test
    fun `should allocate resources for robot`() {
        val orderOutput = robotFactoryService.createRobot(OrderInput(components = arrayOf("A", "D", "F", "I")))
        Assertions.assertNotNull(orderOutput.orderId)
        Assertions.assertNotNull(orderOutput.total)
    }
}