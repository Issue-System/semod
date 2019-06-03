package org.opengroup.archimate.meta.element.technology

import org.opengroup.archimate.meta.element.{BehaviorElement, StrategyCoreStructureBehaviorElement, StrategyCoreStructureBehaviorElementRelationships}

trait TechnologyFunctionElement
	extends BehaviorElement
		with StrategyCoreStructureBehaviorElement
		with TechnologyInternalBehaviorElement

trait TechnologyFunctionElementRelationships[T <: TechnologyFunctionElement]
extends StrategyCoreStructureBehaviorElementRelationships[T]
	with TechnologyInternalBehaviorElementRelationships[T]