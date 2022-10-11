package de.tech26.robotfactory.validator

import de.tech26.robotfactory.config.DBConfig
import de.tech26.robotfactory.exception.RobotConfigInvalidException
import de.tech26.robotfactory.model.OrderInput
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class RobotConfigValidatorTest {
    private val dbConfig: DBConfig = DBConfig()

    @Test
    fun `should return validation error`() {
        assertThrows<RobotConfigInvalidException>("Should throw config invalid exception") {
            RobotConfigValidator.validateRoboConfig(dbConfig.getRobotComponents(), OrderInput(components = arrayOf("A", "C", "D", "F", "I")))
        }
    }

    @Test
    fun `should not return validation error`() {
            RobotConfigValidator.validateRoboConfig(dbConfig.getRobotComponents(), OrderInput(components = arrayOf("C", "D", "F", "I")))
    }

    @Test
    fun `should return that the inventory is available`() {
        assert(true) {
            RobotConfigValidator.isInventoryAvailable(
                dbConfig.getRobotComponentsAvailability(),
                OrderInput(components = arrayOf("A", "D", "F", "I"))
            )
        }
    }

    @Test
    fun `should return that the inventory is not available`() {
        assert(true) {
            !RobotConfigValidator.isInventoryAvailable(dbConfig.getRobotComponentsAvailability(), OrderInput(components = arrayOf("C", "D", "F", "I")))
            }
    }
}