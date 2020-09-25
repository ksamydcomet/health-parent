package com.dcomet.health.dao.impl;

import com.dcomet.health.dao.InventoryDAO;
import com.dcomet.module.inventory.dao.impl.DCometInventoryDAOImpl;
import org.springframework.stereotype.Repository;

/**
 *
 * @author KS
 */
@Repository("inventoryDAO")
public class InventoryDAOImpl extends DCometInventoryDAOImpl implements InventoryDAO {

}
