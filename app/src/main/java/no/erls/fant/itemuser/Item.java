package no.erls.fant.itemuser;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {
    private Long id;
    private String itemTitle;
    private BigDecimal itemPrice;
    private String itemDesc;
    private User itemOwner;
    private User itemBuyer;
    private List<ItemImage> itemImage;

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public User getItemOwner() {
        return itemOwner;
    }

    public void setItemOwner(User itemOwner) {
        this.itemOwner = itemOwner;
    }

    public User getItemBuyer() {
        return itemBuyer;
    }

    public void setItemBuyer(User itemBuyer) {
        this.itemBuyer = itemBuyer;
    }
}
