/**
 * A shopping cart application that demonstrates the use of Bag ADT
 * for managing items in an online shopping scenario.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class ShoppingCartApplication {
    
    /**
     * Inner class representing a shopping item.
     */
    public static class ShoppingItem {
        private String name;
        private double price;
        private String category;
        
        public ShoppingItem(String name, double price, String category) {
            this.name = name;
            this.price = price;
            this.category = category;
        }
        
        public String getName() { return name; }
        public double getPrice() { return price; }
        public String getCategory() { return category; }
        
        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            ShoppingItem item = (ShoppingItem) other;
            return name.equals(item.name) && 
                   Double.compare(item.price, price) == 0 &&
                   category.equals(item.category);
        }
        
        @Override
        public String toString() {
            return String.format("%s ($%.2f) - %s", name, price, category);
        }
    }
    
    /**
     * Shopping cart class using BagInterface.
     */
    public static class ShoppingCart {
        private BagInterface<ShoppingItem> cart;
        private String customerName;
        
        public ShoppingCart(String customerName) {
            this.customerName = customerName;
            this.cart = new ResizableArrayBag<>();
        }
        
        /**
         * Adds an item to the cart.
         */
        public boolean addItem(ShoppingItem item) {
            return cart.add(item);
        }
        
        /**
         * Removes one instance of an item from the cart.
         */
        public boolean removeItem(ShoppingItem item) {
            return cart.remove(item);
        }
        
        /**
         * Removes all instances of an item from the cart.
         */
        public int removeAllInstances(ShoppingItem item) {
            int removed = 0;
            while (cart.contains(item)) {
                if (cart.remove(item)) {
                    removed++;
                } else {
                    break;
                }
            }
            return removed;
        }
        
        /**
         * Gets the quantity of a specific item in the cart.
         */
        public int getItemQuantity(ShoppingItem item) {
            return cart.getFrequencyOf(item);
        }
        
        /**
         * Calculates the total price of items in the cart.
         */
        public double getTotalPrice() {
            double total = 0.0;
            ShoppingItem[] items = cart.toArray();
            
            for (ShoppingItem item : items) {
                total += item.getPrice();
            }
            
            return total;
        }
        
        /**
         * Gets the total number of items in the cart.
         */
        public int getTotalItems() {
            return cart.getCurrentSize();
        }
        
        /**
         * Checks if the cart is empty.
         */
        public boolean isEmpty() {
            return cart.isEmpty();
        }
        
        /**
         * Clears all items from the cart.
         */
        public void clearCart() {
            cart.clear();
        }
        
        /**
         * Gets unique items in the cart (for display purposes).
         */
        public BagInterface<ShoppingItem> getUniqueItems() {
            return BagOperations.getUniqueElements(cart);
        }
        
        /**
         * Gets items by category.
         */
        public BagInterface<ShoppingItem> getItemsByCategory(String category) {
            BagInterface<ShoppingItem> categoryItems = new LinkedBag<>();
            ShoppingItem[] allItems = cart.toArray();
            
            for (ShoppingItem item : allItems) {
                if (item.getCategory().equalsIgnoreCase(category)) {
                    categoryItems.add(item);
                }
            }
            
            return categoryItems;
        }
        
        /**
         * Applies a discount to items in a specific category.
         */
        public double getCategoryTotal(String category) {
            double total = 0.0;
            ShoppingItem[] items = cart.toArray();
            
            for (ShoppingItem item : items) {
                if (item.getCategory().equalsIgnoreCase(category)) {
                    total += item.getPrice();
                }
            }
            
            return total;
        }
        
        /**
         * Generates a detailed cart summary.
         */
        public String getCartSummary() {
            if (isEmpty()) {
                return customerName + "'s cart is empty.";
            }
            
            StringBuilder summary = new StringBuilder();
            summary.append("=== Shopping Cart for ").append(customerName).append(" ===\n");
            
            BagInterface<ShoppingItem> uniqueItems = getUniqueItems();
            ShoppingItem[] unique = uniqueItems.toArray();
            
            for (ShoppingItem item : unique) {
                int quantity = getItemQuantity(item);
                double itemTotal = item.getPrice() * quantity;
                summary.append(String.format("%-20s x%d = $%.2f\n", 
                    item.getName(), quantity, itemTotal));
            }
            
            summary.append("-".repeat(40)).append("\n");
            summary.append(String.format("Total Items: %d\n", getTotalItems()));
            summary.append(String.format("Total Price: $%.2f\n", getTotalPrice()));
            
            return summary.toString();
        }
        
        /**
         * Generates a category breakdown.
         */
        public String getCategoryBreakdown() {
            StringBuilder breakdown = new StringBuilder();
            breakdown.append("=== Category Breakdown ===\n");
            
            // Get all unique categories
            BagInterface<String> categories = new LinkedBag<>();
            ShoppingItem[] items = cart.toArray();
            
            for (ShoppingItem item : items) {
                if (!categories.contains(item.getCategory())) {
                    categories.add(item.getCategory());
                }
            }
            
            String[] categoryArray = categories.toArray();
            for (String category : categoryArray) {
                double categoryTotal = getCategoryTotal(category);
                BagInterface<ShoppingItem> categoryItems = getItemsByCategory(category);
                breakdown.append(String.format("%-15s: %d items, $%.2f\n", 
                    category, categoryItems.getCurrentSize(), categoryTotal));
            }
            
            return breakdown.toString();
        }
        
        public String getCustomerName() {
            return customerName;
        }
    }
    
    /**
     * Demo method showing various shopping cart operations.
     */
    public static void main(String[] args) {
        // Create shopping items
        ShoppingItem laptop = new ShoppingItem("Gaming Laptop", 1299.99, "Electronics");
        ShoppingItem mouse = new ShoppingItem("Wireless Mouse", 29.99, "Electronics");
        ShoppingItem keyboard = new ShoppingItem("Mechanical Keyboard", 89.99, "Electronics");
        ShoppingItem book1 = new ShoppingItem("Data Structures Book", 79.99, "Books");
        ShoppingItem book2 = new ShoppingItem("Algorithm Design", 89.99, "Books");
        ShoppingItem shirt = new ShoppingItem("Programming T-Shirt", 19.99, "Clothing");
        ShoppingItem mug = new ShoppingItem("Code Coffee Mug", 12.99, "Accessories");
        
        // Create shopping cart
        ShoppingCart cart = new ShoppingCart("Alice Johnson");
        
        System.out.println("=== Shopping Cart Demo ===\n");
        
        // Add items to cart
        System.out.println("Adding items to cart...");
        cart.addItem(laptop);
        cart.addItem(mouse);
        cart.addItem(mouse); // Add duplicate
        cart.addItem(keyboard);
        cart.addItem(book1);
        cart.addItem(book2);
        cart.addItem(shirt);
        cart.addItem(shirt); // Add duplicate
        cart.addItem(mug);
        
        // Display cart summary
        System.out.println(cart.getCartSummary());
        
        // Display category breakdown
        System.out.println(cart.getCategoryBreakdown());
        
        // Demonstrate item operations
        System.out.println("=== Item Operations ===");
        System.out.println("Mouse quantity: " + cart.getItemQuantity(mouse));
        System.out.println("Shirt quantity: " + cart.getItemQuantity(shirt));
        
        // Remove one shirt
        System.out.println("\nRemoving one shirt...");
        cart.removeItem(shirt);
        System.out.println("Shirt quantity after removal: " + cart.getItemQuantity(shirt));
        
        // Remove all mice
        System.out.println("\nRemoving all mice...");
        int miceRemoved = cart.removeAllInstances(mouse);
        System.out.println("Mice removed: " + miceRemoved);
        System.out.println("Mouse quantity after removal: " + cart.getItemQuantity(mouse));
        
        // Final cart summary
        System.out.println("\n=== Final Cart Summary ===");
        System.out.println(cart.getCartSummary());
        
        // Demonstrate cart operations
        System.out.println("=== Cart Operations Demo ===");
        
        // Create another cart for comparison
        ShoppingCart cart2 = new ShoppingCart("Bob Smith");
        cart2.addItem(laptop);
        cart2.addItem(book1);
        cart2.addItem(mug);
        
        System.out.println("Cart 2 Summary:");
        System.out.println(cart2.getCartSummary());
        
        // Create union of carts (combined shopping)
        BagInterface<ShoppingItem> combinedCart = BagOperations.union(
            cart.getUniqueItems(), cart2.getUniqueItems());
        
        System.out.println("Combined unique items from both carts:");
        System.out.println(BagOperations.bagToString(combinedCart));
        
        // Find common items
        BagInterface<ShoppingItem> commonItems = BagOperations.intersection(
            cart.getUniqueItems(), cart2.getUniqueItems());
        
        System.out.println("\nCommon items in both carts:");
        System.out.println(BagOperations.bagToString(commonItems));
        
        // Checkout simulation
        System.out.println("\n=== Checkout Process ===");
        System.out.println("Processing checkout for " + cart.getCustomerName());
        System.out.println("Final total: $" + String.format("%.2f", cart.getTotalPrice()));
        System.out.println("Thank you for shopping with us!");
        
        // Clear cart after checkout
        cart.clearCart();
        System.out.println("Cart cleared. Is empty: " + cart.isEmpty());
    }
} 