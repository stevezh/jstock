/*
 * JStock - Free Stock Market Software
 * Copyright (C) 2015 Yan Cheng Cheok <yccheok@yahoo.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */


package org.yccheok.jstock.gui;

import org.yccheok.jstock.engine.Country;
import org.yccheok.jstock.engine.PriceSource;
import org.yccheok.jstock.portfolio.DecimalPlace;

/**
 *
 * @author  yccheok
 */
public class OptionsJDialog extends javax.swing.JDialog implements JStockOptionsObserver {
    
    /** Creates new form OptionsJDialog */
    public OptionsJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("JStock Options");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        optionsJPanel = new OptionsJPanel();
        getContentPane().add(optionsJPanel, java.awt.BorderLayout.CENTER);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/apply.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/yccheok/jstock/data/gui"); // NOI18N
        jButton1.setText(bundle.getString("OptionsJDialog_OK")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/16x16/button_cancel.png"))); // NOI18N
        jButton2.setText(bundle.getString("OptionsJDialog_Cancel")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        final Country country = jStockOptions.getCountry();
        
        final int oldScanningSpeed = jStockOptions.getScanningSpeed();
        final boolean oldIsFeeCalculationEnabled = jStockOptions.isFeeCalculationEnabled();
        final boolean oldIsDynamicChartVisible = jStockOptions.isDynamicChartVisible();
        final DecimalPlace oldDecimalPlace = jStockOptions.getDecimalPlace();
        final boolean oldIsCurrencyExchangeEnable = jStockOptions.isCurrencyExchangeEnable(country);
        final PriceSource oldPriceSource = jStockOptions.getPriceSource(country);
        final Country oldLocalCurrencyCountry = jStockOptions.getLocalCurrencyCountry(country);        
        
        if (apply(jStockOptions) == false) {
            return;
        }

        if (oldPriceSource != jStockOptions.getPriceSource(jStockOptions.getCountry())) {
            JStock.instance().updatePriceSource(jStockOptions.getCountry(), jStockOptions.getPriceSource(jStockOptions.getCountry()));
        }
        if (oldScanningSpeed != jStockOptions.getScanningSpeed()) {
            JStock.instance().refreshAllRealTimeStockMonitors();
            JStock.instance().refreshRealTimeIndexMonitor();
        }
        if (oldIsFeeCalculationEnabled != jStockOptions.isFeeCalculationEnabled()) {
            JStock.instance().getPortfolioManagementJPanel().refreshGUIAfterFeeCalculationEnabledOptionsChanged();
        }
        if (oldIsDynamicChartVisible != jStockOptions.isDynamicChartVisible()) {
            JStock.instance().initDynamicChartVisibility();
        }        
        if (oldIsCurrencyExchangeEnable != jStockOptions.isCurrencyExchangeEnable(country) || oldLocalCurrencyCountry != jStockOptions.getLocalCurrencyCountry(country) || oldDecimalPlace != jStockOptions.getDecimalPlace()) {
            JStock.instance().getPortfolioManagementJPanel().refreshGUIAfterOptionsJDialog();
        }
        
        this.setVisible(false);
        this.dispose();        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        optionsJPanel.cancel();
    }//GEN-LAST:event_formWindowClosed

    @Override
    public void set(JStockOptions jStockOptions) {
        optionsJPanel.set(jStockOptions);
        this.jStockOptions = jStockOptions;
    }

    @Override
    public boolean apply(JStockOptions jStockOptions) {
        return optionsJPanel.apply(jStockOptions);
    }

    /**
     * Selects the desired options page.
     *
     * @param buttonMapKey title of the page
     */
    public void select(String buttonMapKey) {
        optionsJPanel.select(buttonMapKey);
    }

    private OptionsJPanel optionsJPanel;
    private JStockOptions jStockOptions;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    
}
