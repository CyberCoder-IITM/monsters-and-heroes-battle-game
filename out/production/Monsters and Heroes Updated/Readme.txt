# CS611-Assignment 4
## Monsters and Heroes
---------------------------------------------------------------------------
- Name: Saleeq Adnan Syed
- Email: Saleeq@bu.edu
- Student ID: U34331475
---------------------------------------------------------------------------

Overview
Monsters and Heroes is a turn-based RPG game where players lead a party of heroes through battles against various monsters. The game features a rich set of classes, items, and mechanics that provide an engaging gameplay experience.

-------------------------------------------------------
Factory Pattern Implementation: The factory pattern is used to dynamically create heroes and monsters based on user input or random generation.

---------------------------------------------------------

Characters

GameCharacter.java: Abstract class for shared attributes and methods.
Heroes/Hero.java: Represents a generic hero with basic attributes.
Heroes/Paladin.java: A hero subclass with enhanced strength.
Heroes/Sorcerer.java: A hero subclass skilled in magic.
Heroes/Warrior.java: A hero subclass focused on physical attacks.
Monsters/Dragon.java: Represents a dragon monster with unique powers.
Monsters/Exoskeleton.java: A monster type with high defense.
Monsters/Monster.java: Generic class for monsters.
Monsters/Spirit.java: A monster type skilled in magical attacks.
Exception Handling
InputHandler.java: Manages input validation and handles exceptions.
---------------------------------------------------------

Items
Armor.java: Represents armor items for protection.
Item.java: Abstract class for items.
Potion.java: Represents potions for health or stats recovery.
Spell.java: Represents spells for heroes to cast.
Weapon.java: Represents weapons for dealing damage.
Market Area
Market.java: Facilitates buying and selling of items.
---------------------------------------------------------

Spaces
CommonSpace.java: Traversable area with potential monster encounters.
InaccessibleSpace.java: Blocked area on the map.
MarketSpace.java: Area designated for market transactions.
Core Classes
Battle.java: Manages combat encounters and turn-based mechanics.
Game.java: Main controller for game initialization and flow.
Main.java: Entry point of the application.
World.java: Manages spaces, character movement, and interactions.
---------------------------------------------------------

Cool Features
Real-time Board Display: Shows the current state of the game board with hero positions updated in real-time.
Modular Design: Organized into packages based on roles such as Behaviors, Characters, Items, and Spaces, making it easy to extend or modify.
Input Handler: Prevents invalid inputs throughout the game interaction, enhancing user experience.
Factory Pattern Implementation: The factory pattern is used to dynamically create heroes and monsters based on user input or random generation.

---------------------------------------------------------
Run the Main class to start the game.
Gameplay
Choose the number of heroes in your party (1–3).
Enter names for each hero and select their class (Warrior, Sorcerer, Paladin).
Navigate through different spaces using W/A/S/D keys.
Hero Classes
Warrior: Strong physical attackers with high strength.
Sorcerer: Magic users with powerful spells and high mana points.
Paladin: Balanced fighters with both physical and magical abilities.
Items
Weapons increase attack damage.
Armor reduces incoming damage.
Potions restore health or mana.
Spells cast powerful magical attacks on enemies.
---------------------------------------
Battle System
Turn-based combat where heroes and monsters take turns to attack. Choose actions like attacking, casting spells, using potions, or changing equipment.
Market
Buy and sell items like weapons, armor, potions, and spells. Ensure you have enough gold and meet level requirements to purchase items.
Leveling Up
Gain experience points from battles to level up heroes. Leveling up increases stats like HP, MP, Strength, Dexterity, and Agility.

--------------------------------------
How many heroes in your party? (1-3): 2
Enter hero name:
Adnan
Choose hero type (Warrior/Sorcerer/Paladin):
Warrior
Enter hero name:
Harsha
Choose hero type (Warrior/Sorcerer/Paladin):
Sorcererf
Invalid hero type. Please try again.
Choose hero type (Warrior/Sorcerer/Paladin):
Sorcerer
M  C  C  C  M  M  C  M
M  C  C  X  C  C  X  M
C  C  C  C  C  AD X  C
C  M  C  X  C  C  X  M
M  C  M  C  HA C  X  M
M  C  C  C  C  M  M  C
C  C  C  C  M  X  M  M
X  M  C  C  C  C  C  C
Enter move (W/A/S/D) or action (I/M/Q):
w
Adnan moved W
Harsha moved W
Battle starts!

Adnan's turn:
1. Attack
2. Use Potion
3. Change Weapon/Armor
4. Show Info
Choose an action: 1
Select a monster to target:
1. Monster 1
2. Monster 2
Enter your choice: 1
Adnan attacked Monster 1 for 16 damage!

Harsha's turn:
1. Attack
2. Cast Spell
3. Use Potion
4. Change Weapon/Armor
5. Show Info
Choose an action: 2
Choose a spell to cast:
1. Fireball (Level 1 fire Spell) - Damage: 30, Mana Cost: 25, Price: 100 gold, Uses left: 30
2. Ice Shard (Level 1 ice Spell) - Damage: 25, Mana Cost: 20, Price: 100 gold, Uses left: 25
1
Select a monster to target:
1. Monster 1
2. Monster 2
Enter your choice: 2
Monster 2's defense has been reduced!
Harsha cast Fireball on Monster 2 for 33 damage!
Monster 1 attacked Adnan for 28 damage!
Monster 2 attacked Adnan for 16 damage!

Adnan's turn:
1. Attack
2. Use Potion
3. Change Weapon/Armor
4. Show Info
Choose an action: 3
1. Change Weapon
2. Change Armor
Choose an option: 1
Adnan has no weapons to equip!

Adnan's turn:
1. Attack
2. Use Potion
3. Change Weapon/Armor
4. Show Info
Choose an action: 4

Heroes:
Warrior Adnan (Level 1 Warrior) - HP: 66/100, MP: 50/50, Strength: 10, Dexterity: 5, Agility: 8, Experience: 0, Gold: 500
Sorcerer Harsha (Level 1 Sorcerer) - HP: 100/100, MP: 107/120, Strength: 5, Dexterity: 10, Agility: 8, Experience: 0, Gold: 500

Monsters:
Dragon Monster 1 (Level 1 Dragon) - HP: 94/100, Damage: 24, Defense: 10, Dodge Chance: 10.00%
Exoskeleton Monster 2 (Level 1 Exoskeleton) - HP: 88/100, Damage: 15, Defense: 16, Dodge Chance: 15.00%

Adnan's turn:
1. Attack
2. Use Potion
3. Change Weapon/Armor
4. Show Info
Choose an action: 2
Choose a potion to use:
1. Health Potion (Level 1 Potion) - Heals: 50 HP, Price: 50 gold, Uses left: 1
2. Mana Potion (Level 1 Potion) - Heals: 35 HP, Price: 35 gold, Uses left: 1
1
Adnan used Health Potion and healed for 50 HP.
Adnan used Health Potion!

Harsha's turn:
1. Attack
2. Cast Spell
3. Use Potion
4. Change Weapon/Armor
5. Show Info
Choose an action: 1
Select a monster to target:
1. Monster 1
2. Monster 2
Enter your choice: 2
Harsha attacked Monster 2 for 15 damage!
Monster 1 attacked Harsha for 21 damage!
Monster 2 attacked Adnan for 12 damage!

Adnan's turn:
1. Attack
2. Use Potion
3. Change Weapon/Armor
4. Show Info
Choose an action:

Market System:
How many heroes in your party? (1-3): 2
Enter hero name:
Adnan
Choose hero type (Warrior/Sorcerer/Paladin):
Warrior
Enter hero name:
Kasi
Choose hero type (Warrior/Sorcerer/Paladin):
Paladin
M  M  C  M  M  C  X  C
X  C  M  M  C  M  C  X
X  M  M  M  C  M  C  C
M  AD C  C  M  X  C  M
C  M  C  M  M  KA C  C
M  C  C  X  C  C  C  C
C  C  C  X  X  X  M  C
M  C  C  C  M  M  C  M
Enter move (W/A/S/D) or action (I/M/Q):
I
Name: Adnan (Warrior), Level: 1, HP: 100/100, MP: 50/50, Strength: 10, Dexterity: 5, Agility: 8, Gold: 500
Name: Kasi (Paladin), Level: 1, HP: 100/100, MP: 80/80, Strength: 9, Dexterity: 8, Agility: 6, Gold: 500
M  M  C  M  M  C  X  C
X  C  M  M  C  M  C  X
X  M  M  M  C  M  C  C
M  AD C  C  M  X  C  M
C  M  C  M  M  KA C  C
M  C  C  X  C  C  C  C
C  C  C  X  X  X  M  C
M  C  C  C  M  M  C  M
Enter move (W/A/S/D) or action (I/M/Q):
M

Welcome to the market, Adnan!
Your gold: 500
1. Buy item
2. Sell item
3. Exit market
Choose an option: 1

Items for sale:
1. Sword0 (Level 2 Weapon) - Damage: 13, Hands Required: 1, Price: 732 gold
2. Shield0 (Level 1 Armor) - Damage Reduction: 36, Price: 361 gold
3. Health Potion0 (Level 5 Potion) - Heals: 241 HP, Price: 118 gold, Uses left: 1
4. Fireball0 (Level 5 Fire Spell) - Damage: 38, Mana Cost: 59, Price: 540 gold, Uses left: 38
5. Sword1 (Level 3 Weapon) - Damage: 66, Hands Required: 1, Price: 938 gold
6. Shield1 (Level 2 Armor) - Damage Reduction: 13, Price: 275 gold
7. Health Potion1 (Level 1 Potion) - Heals: 282 HP, Price: 156 gold, Uses left: 1
8. Fireball1 (Level 3 Fire Spell) - Damage: 76, Mana Cost: 107, Price: 335 gold, Uses left: 76
9. Sword2 (Level 1 Weapon) - Damage: 87, Hands Required: 1, Price: 401 gold
10. Shield2 (Level 5 Armor) - Damage Reduction: 9, Price: 294 gold
11. Health Potion2 (Level 3 Potion) - Heals: 163 HP, Price: 409 gold, Uses left: 1
12. Fireball2 (Level 2 Fire Spell) - Damage: 91, Mana Cost: 170, Price: 225 gold, Uses left: 91
13. Sword3 (Level 5 Weapon) - Damage: 82, Hands Required: 1, Price: 207 gold
14. Shield3 (Level 1 Armor) - Damage Reduction: 35, Price: 360 gold
15. Health Potion3 (Level 2 Potion) - Heals: 460 HP, Price: 496 gold, Uses left: 1
16. Fireball3 (Level 1 Fire Spell) - Damage: 28, Mana Cost: 191, Price: 268 gold, Uses left: 28
17. Sword4 (Level 4 Weapon) - Damage: 64, Hands Required: 1, Price: 543 gold
18. Shield4 (Level 2 Armor) - Damage Reduction: 40, Price: 119 gold
19. Health Potion4 (Level 3 Potion) - Heals: 100 HP, Price: 404 gold, Uses left: 1
20. Fireball4 (Level 5 Fire Spell) - Damage: 68, Mana Cost: 104, Price: 708 gold, Uses left: 68
21. Sword5 (Level 3 Weapon) - Damage: 55, Hands Required: 1, Price: 751 gold
22. Shield5 (Level 3 Armor) - Damage Reduction: 29, Price: 663 gold
23. Health Potion5 (Level 3 Potion) - Heals: 490 HP, Price: 402 gold, Uses left: 1
24. Fireball5 (Level 3 Fire Spell) - Damage: 76, Mana Cost: 177, Price: 814 gold, Uses left: 76
25. Sword6 (Level 2 Weapon) - Damage: 47, Hands Required: 1, Price: 244 gold
26. Shield6 (Level 4 Armor) - Damage Reduction: 17, Price: 437 gold
27. Health Potion6 (Level 3 Potion) - Heals: 385 HP, Price: 273 gold, Uses left: 1
28. Fireball6 (Level 1 Fire Spell) - Damage: 45, Mana Cost: 80, Price: 640 gold, Uses left: 45
29. Sword7 (Level 5 Weapon) - Damage: 92, Hands Required: 1, Price: 885 gold
30. Shield7 (Level 4 Armor) - Damage Reduction: 22, Price: 304 gold
31. Health Potion7 (Level 1 Potion) - Heals: 333 HP, Price: 103 gold, Uses left: 1
32. Fireball7 (Level 2 Fire Spell) - Damage: 79, Mana Cost: 186, Price: 244 gold, Uses left: 79
33. Sword8 (Level 2 Weapon) - Damage: 44, Hands Required: 1, Price: 656 gold
34. Shield8 (Level 4 Armor) - Damage Reduction: 7, Price: 491 gold
35. Health Potion8 (Level 5 Potion) - Heals: 118 HP, Price: 365 gold, Uses left: 1
36. Fireball8 (Level 5 Fire Spell) - Damage: 72, Mana Cost: 84, Price: 276 gold, Uses left: 72
37. Sword9 (Level 4 Weapon) - Damage: 80, Hands Required: 1, Price: 415 gold
38. Shield9 (Level 4 Armor) - Damage Reduction: 31, Price: 706 gold
39. Health Potion9 (Level 5 Potion) - Heals: 124 HP, Price: 276 gold, Uses left: 1
40. Fireball9 (Level 1 Fire Spell) - Damage: 35, Mana Cost: 141, Price: 994 gold, Uses left: 35
Enter the number of the item you want to buy (0 to cancel): 0

Welcome to the market, Adnan!
Your gold: 500
1. Buy item
2. Sell item
3. Exit market
Choose an option: 2

Your inventory:
1. Health Potion (Level 1 Potion) - Heals: 50 HP, Price: 50 gold, Uses left: 1
2. Mana Potion (Level 1 Potion) - Heals: 35 HP, Price: 35 gold, Uses left: 1
Enter the number of the item you want to sell (0 to cancel): 0

Welcome to the market, Adnan!
Your gold: 500
1. Buy item
2. Sell item
3. Exit market
Choose an option: 3

Welcome to the market, Kasi!
Your gold: 500
1. Buy item
2. Sell item
3. Exit market
Choose an option: 3
M  M  C  M  M  C  X  C
X  C  M  M  C  M  C  X
X  M  M  M  C  M  C  C
M  AD C  C  M  X  C  M
C  M  C  M  M  KA C  C
M  C  C  X  C  C  C  C
C  C  C  X  X  X  M  C
M  C  C  C  M  M  C  M
Enter move (W/A/S/D) or action (I/M/Q):


