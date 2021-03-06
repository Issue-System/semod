package ru.kvb74.semod.opengroup.archimate.meta.element.physical

import ru.kvb74.semod.meta.{ElementName, RR}
import ru.kvb74.semod.opengroup.archimate.meta.element._
import ru.kvb74.semod.opengroup.archimate.meta.element.technology.PathElement

trait DistributionNetworkElement
	extends ActiveStructureElement
		with StrategyCoreStructureBehaviorElement

case object DistributionNetworkElement
	extends ElementName

trait DistributionNetworkElementProps[T <: DistributionNetworkElement]
	extends StrategyCoreStructureBehaviorElementProps[T]
	
	/**
	* @see http://pubs.opengroup.org/architecture/archimate3-doc/chap11.html#_Toc489946104
	*/
trait DistributionNetworkElementRelationships[T <: DistributionNetworkElement]
	extends StrategyCoreStructureBehaviorElementRelationships[T] {

	def realizes(dst: PathElement): T = tt._rel.realizes(dst)

	// лишнее ограничение уже есть в ElementRelationships
	//	def associatedWith(dst: FacilityElement): T = tt._rel.associatedWith(dst)

	def aggregates(dst: FacilityElement): T = tt._rel.aggregates(dst)

	def aggregates(dst: EquipmentElement): T = tt._rel.aggregates(dst)

	// лишнее ограничение уже есть в ElementRelationships
	//	def associatedWith(dst: MaterialElement): T = tt._rel.associatedWith(dst)

	_register(DistributionNetworkElement,
		RR.realizes(PathElement),
		//		JR.associatedWith(FacilityElement),
		RR.aggregates(FacilityElement),
		RR.aggregates(EquipmentElement),
		//		JR.associatedWith(MaterialElement),
	)

}

