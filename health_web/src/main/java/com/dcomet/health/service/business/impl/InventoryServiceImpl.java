package com.dcomet.health.service.business.impl;


import com.dcomet.health.service.business.InventoryService;
import com.dcomet.module.inventory.service.impl.DCometInventoryServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author KS
 */
@Service("inventoryService")
@Transactional(propagation = Propagation.SUPPORTS)
public class InventoryServiceImpl extends DCometInventoryServiceImpl implements InventoryService{

}
