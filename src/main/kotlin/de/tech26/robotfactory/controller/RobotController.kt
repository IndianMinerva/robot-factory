package de.tech26.robotfactory.controller

import de.tech26.robotfactory.exception.InventoryNotAvailableException
import de.tech26.robotfactory.exception.RobotConfigInvalidException
import de.tech26.robotfactory.model.OrderInput
import de.tech26.robotfactory.model.OrderOutput
import de.tech26.robotfactory.service.RobotFactoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RobotController(val robotFactoryService: RobotFactoryService)
{
    @PostMapping("/orders")
    fun getHello(@RequestBody orderInput: OrderInput):ResponseEntity<OrderOutput> {
        try {
            return ResponseEntity(robotFactoryService.createRobot(orderInput), HttpStatus.CREATED)
        } catch (rcie: RobotConfigInvalidException) {
            return ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)
        } catch (inve: InventoryNotAvailableException) {
            return ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }
}