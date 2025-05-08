package com.fsse2502.fsse_project.data.cartItem.dto.response;

import com.fsse2502.fsse_project.data.cartItem.domainObject.response.EffectiveStockResponseData;

public class EffectiveStockResponseDto {
    private Integer effectiveStock;

    public EffectiveStockResponseDto(EffectiveStockResponseData data) {
        this.effectiveStock = data.getEffectiveStock();
    }

    public Integer getEffectiveStock() {
        return effectiveStock;
    }

    public void setEffectiveStock(Integer effectiveStock) {
        this.effectiveStock = effectiveStock;
    }
}
