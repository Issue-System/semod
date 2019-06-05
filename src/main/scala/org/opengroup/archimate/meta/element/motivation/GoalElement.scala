package org.opengroup.archimate.meta.element.motivation

import org.opengroup.archimate.meta.element.{ElementName, JR}

trait GoalElement
	extends MotivationElement

case object GoalElement
	extends ElementName

trait GoalElementRelationships[T <: GoalElement]
	extends MotivationElementRelationships[T] {

	def associatedWith(dst: AssessmentElement): T = tt._rel.associatedWith(dst)

	def associatedWith(dst: DriverElement): T = tt._rel.associatedWith(dst)

	def associatedWith(dst: ValueElement): T = tt._rel.associatedWith(dst)

	def associatedWith(dst: MeaningElement): T = tt._rel.associatedWith(dst)

	_register(GoalElement,
		JR.associatedWith(AssessmentElement),
		JR.associatedWith(DriverElement),
		JR.associatedWith(ValueElement),
		JR.associatedWith(MeaningElement),
	)

}
