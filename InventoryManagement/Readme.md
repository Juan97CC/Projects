# DataStructGame - Inventory and Shop Management Game

## Overview
DataStructGame is a console-based inventory and shop management game written in Java. The game allows the player to interact with a shop where they can buy and manage weapons, check their inventory, and view their player’s statistics. The game uses basic data structures such as linked lists and arrays to handle the inventory system.

## Features
- **Player and Inventory Management**: Players can buy weapons, add/remove them to/from their inventory, and view their stats.
- **Shop System**: Items (weapons) can be added or removed from the shop by the player.
- **Backpack/Inventory**: The player can store a limited number of items in their backpack (inventory), which has a weight limit.
- **Weapon Attributes**: Each weapon has a name, range, damage, weight, and cost.

## Classes Overview

### `Player`
- Represents the player in the game.
- Holds the player's name, money, and inventory (backpack).
- Can buy weapons, remove items, and print character stats.

### `Weapon`
- Represents a weapon in the shop.
- Contains attributes such as `weaponName`, `range`, `damage`, `weight`, and `cost`.

### `ShopItem`
- Represents a weapon in the shop along with its quantity in stock.
- Contains a reference to a `Weapon` object and a `numberInStock`.

### `Node`
- A node in the linked list used to store `Weapon` objects.

### `LinkedList`
- A custom implementation of a singly linked list that stores `Weapon` objects.
- Provides methods for inserting, deleting, and searching weapons.

### `ArrayManager`
- Manages an inventory of weapons using an array-based hash table.
- Handles adding, searching, and deleting weapons from the shop.

### `Assignment2_ds`
- The main entry point for the game.
- Contains the game’s menu system and handles user interactions such as buying and adding weapons, viewing the inventory, and managing the shop.

## How to Play
1. **Start the Game**: On startup, the game will prompt you to enter your player name.
2. **Main Menu**: Once you enter the game, you'll be presented with a menu that allows you to:
   - Add new weapons to the shop.
   - Delete weapons from the shop.
   - Buy weapons from the shop.
   - View and manage your backpack.
   - View your player stats.
   - Exit the game.
3. **Weapon Management**:
   - You can buy weapons from the shop as long as you have enough money and your backpack has space.
   - Your backpack has a weight limit, so be careful about the total weight of items you add.
   - You can also remove weapons from your backpack if needed.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your machine.

### Running the Game
1. Clone the repository or download the source code.
2. Open the project in your preferred java IDE and run the program.


