package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "Add Failed";
    }

    @ShellMethod(value = "Print the Cart", key = "pc")
    public String printCart(){
        if(this.posService.getCart() == null){
            return "You don't have a cart, Press 'n' to create one";
        }
        return this.posService.getCart().toString();
    }

    @ShellMethod(value = "Modify product from Cart", key = "m")
    public String modifyCart(String productId, int amount){
        if (posService.modify(productId, amount)) {
            return posService.getCart().toString();
        }
        return "Modify Failed";
    }

    @ShellMethod(value = "Empty the Cart", key = "e")
    public String emptyCart(){
        if (posService.empty()) {
            return posService.getCart().toString();
        }
        return "Empty Failed";
    }

    @ShellMethod(value = "Delete the Item from Cart", key = "d")
    public String deleteCart(String productId){
        if (posService.delete(productId)) {
            return "Delete Success\n" + posService.getCart().toString();
        }
        return "Delete Failed";
    }

}
