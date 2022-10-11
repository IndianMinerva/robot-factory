package de.tech26.robotfactory.exception

sealed class RobotFactoryException(message: String): RuntimeException(message)

class RobotConfigInvalidException(message: String): RobotFactoryException(message)

class InventoryNotAvailableException(message: String): RobotFactoryException(message)
