package de.tech26.robotfactory.config

import de.tech26.robotfactory.model.Robot
import de.tech26.robotfactory.model.RobotComponent
import de.tech26.robotfactory.model.RobotComponentType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DBConfig {
    @Bean  //RobotComponents Table
    fun getRobotComponents(): Map<String, RobotComponent> {
        val robotComponents = listOf(
            RobotComponent("A", 10.28f, "Humanoid Face", RobotComponentType.FACE),
            RobotComponent("B", 24.07f, "LCD Face", RobotComponentType.FACE),
            RobotComponent("C", 13.30f, "Steampunk Face", RobotComponentType.FACE),

            RobotComponent("D", 28.94f, "Arms with Hands", RobotComponentType.ARMS),
            RobotComponent("E", 12.39f, "Arms with Grippers", RobotComponentType.ARMS),

            RobotComponent("F", 30.77f, "Mobility with Wheels", RobotComponentType.MOBILITY),
            RobotComponent("G", 55.13f, "Mobility with Legs", RobotComponentType.MOBILITY),
            RobotComponent("H", 50.00f, "Mobility with Tracks", RobotComponentType.MOBILITY),

            RobotComponent("I", 90.12f, "Material Bioplastic", RobotComponentType.MATERIAL),
            RobotComponent("J", 82.31f, "Material Metallic", RobotComponentType.MATERIAL)
        )
        return  robotComponents.associate { Pair(it.id, it) }
    }

    @Bean //Availability table
    fun getRobotComponentsAvailability(): MutableMap<String, Int> {
        val inventory = HashMap<String, Int>();
        inventory.putAll(
            mapOf(
                Pair("A", Integer.valueOf(9)),
                Pair("B", Integer.valueOf(7)),
                Pair("C", Integer.valueOf(0)),
                Pair("D", Integer.valueOf(1)),
                Pair("E", Integer.valueOf(3)),
                Pair("F", Integer.valueOf(2)),
                Pair("G", Integer.valueOf(15)),
                Pair("H", Integer.valueOf(7)),
                Pair("I", Integer.valueOf(92)),
                Pair("J", Integer.valueOf(15))
            )
        )
        return inventory
    }

    @Bean
    fun getRobots(): HashSet<Robot> {  //Robots table
        return HashSet<Robot>()
    }
}