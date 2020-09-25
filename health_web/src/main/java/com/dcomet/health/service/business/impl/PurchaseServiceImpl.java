package com.dcomet.health.service.business.impl;


import com.dcomet.health.service.business.PurchaseService;
import com.dcomet.module.purchase.service.impl.DCometPurchaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author KS
 */
@Service("purchaseService")
@Transactional(propagation = Propagation.SUPPORTS)
public class PurchaseServiceImpl extends DCometPurchaseServiceImpl implements PurchaseService{
    
}
