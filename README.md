# Monsters Vs Government

A custom surival mod that forces you to pick a side, or be termiated. Like plants vs zombies but way more deadly. You will find unusual stuff. Discover things. But be warned, this mod is not for the faint-hearted.

---

## The Faction System
When you first spawn into the world, you are presented with the **Identity Selection** screen. This choice defines your path and how the world perceives you.

* **Neutral (ID 0):** You start as a civilian. You have no special powers or gear, but you are not yet a target.
* **Monster (ID 1):** You have embraced the shadows. You gain access to biological weaponry and spiritual upgrades.
* **Government (ID 2):** You represent law and order. You use tactical precision and heavy metal to suppress the monster uprising.

The system saves your **FactionID** to your player data immediately, ensuring your identity persists every time you log back in.

---

## The Armory: Items & Equipment

### **Decayed Dagger**
The standard-issue blade for the newly turned. It’s rusted and brittle, equivalent to stone tier, but it serves as the essential base for more powerful upgrades.
* **Damage:** 3
* **Attack Speed:** -2.0

### **Spirit Blood**
A glowing, universal purple essence harvested from the spirits of the world (like the Sky Spirit). It is the vital ingredient for all Monster-tier upgrades. Without this, your equipment remains weak and decaying.

### **Repaired Dagger**
A purified version of the Decayed Dagger. It has been reinforced with iron and gold, then infused with Spirit Blood to grant it supernatural properties.
* **Damage:** 5
* **Special Ability: Shadow Blindness.** Every strike has a 10% chance to inflict total blindness on your target for 5 seconds.

---

## The Rituals: Custom Crafting

The mod uses a unique **Stack-Size Crafting** system. Unlike standard Minecraft recipes, certain rituals require a specific amount of items in a single slot to work.

### **Restoring the Dagger**
To upgrade a **Decayed Dagger** into a **Repaired Dagger**, you must arrange the following in a Crafting Table:

| Position | Item |
| :--- | :--- |
| **Top Row** | Iron Ingot, Iron Ingot, Iron Ingot |
| **Middle Row** | Iron Ingot, **Spirit Blood**, Iron Ingot |
| **Bottom Row** | **Decayed Dagger**, (Empty), **3x Gold Ingots** |

**Note:** The bottom-right slot *must* contain a stack of exactly **3 Gold Ingots**. If you put more or less, the ritual will fail and no output will appear.

---

## Development Notes
This mod is built using the Fabric API. It utilizes custom Recipe Serializers to handle stack-specific crafting and NBT-based persistent data for faction tracking.
