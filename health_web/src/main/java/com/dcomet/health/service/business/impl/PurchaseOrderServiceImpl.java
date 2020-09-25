package com.dcomet.health.service.business.impl;


import com.dcomet.module.purchase.service.impl.DCometPurchaseOrderServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author KS
 */
@Service("purchaseOrderService")
@Transactional(propagation = Propagation.SUPPORTS)
public class PurchaseOrderServiceImpl extends DCometPurchaseOrderServiceImpl{   
  
}
