# ⚔️ Monsters and Heroes Battle Game

> A turn-based RPG battle system featuring three hero classes, multiple monster types, dynamic item management, and strategic combat mechanics built with pure Java.

[![Java](https://img.shields.io/badge/Language-Java-orange?style=flat-square&logo=java)](#)
[![Assignment](https://img.shields.io/badge/Type-CS611%20Assignment%204-blue?style=flat-square)](#)
[![Status](https://img.shields.io/badge/Status-Complete-success?style=flat-square)](#)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Game Mechanics](#game-mechanics)
- [Characters](#characters)
  - [Heroes](#heroes)
  - [Monsters](#monsters)
- [Item System](#item-system)
- [Architecture & Design Patterns](#architecture--design-patterns)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [How to Play](#how-to-play)
- [Codebase Analysis](#codebase-analysis)
- [Author](#author)

---

## 🎮 Overview

**Monsters and Heroes** is an immersive turn-based RPG (Role-Playing Game) that challenges players to lead a party of 1-3 heroes through an 8×8 grid-based world. Encounter procedurally generated monsters, explore the dynamic marketplace, engage in strategic turn-based battles, and manage resources to survive increasingly challenging encounters.

The game features:

- **Procedural World Generation**: Each playthrough creates a unique 8×8 world with accessible commonspaces, market areas, and inaccessible obstacles
- **Three Hero Classes**: Warrior, Sorcerer, Paladin—each with unique abilities and stat progression
- **Three Monster Types**: Dragon, Spirit, Exoskeleton—each with distinct combat mechanics
- **Rich Item Economy**: Weapons, Armor, Potions, and Spells for varied combat strategies
- **Dynamic Combat System**: Turn-based battles with special abilities, dodge mechanics, and status effects
- **Progression System**: Level up, gain experience, farm gold, and upgrade equipment

---

## ✨ Features

### Core Gameplay

- ✅ **Hero Party System** - Recruit and manage 1-3 heroes with individual stats and inventories
- ✅ **Turn-Based Combat** - Strategic, alternating attack phases with special abilities
- ✅ **Procedural Generation** - Randomly generated worlds ensure unique playthroughs
- ✅ **Market Trading System** - Buy/sell items, manage gold, upgrade equipment
- ✅ **Level Progression** - Experience-based leveling with stat growth
- ✅ **Monster Encounters** - Dynamic encounters in common spaces

### Character Customization

- ✅ **Multiple Hero Classes** - Distinct playstyles with unique stat distributions
- ✅ **Equipment System** - Equip weapons and armor with level/stat requirements
- ✅ **Spell Casting** - Magic system with mana costs and elemental effects
- ✅ **Potion Management** - Health and mana recovery consumables

### Advanced Mechanics

- ✅ **Special Abilities** - Class-specific attacks and bonuses
- ✅ **Dodge System** - Monsters have chance-based evasion; spells apply debuffs
- ✅ **Stat Scaling** - Weapon damage and spell power scale with hero stats
- ✅ **Status Effects** - Temporary stat reductions from spell effects

---

## 🎯 Game Mechanics

### Movement & Exploration

- **Grid Navigation**: Move your hero party (W/A/S/D) on an 8×8 world grid
- **Space Types**:
  - **Common Spaces** (50%): Safe areas, potential monster encounters
  - **Market Spaces** (30%): Trade gear and items
  - **Inaccessible Spaces** (20%): Blocked terrain, cannot traverse

### Combat System

#### Hero Turn

Each hero in turn performs ONE action:

1. **Attack** - Deal weapon-based damage to a monster
2. **Cast Spell** - Spend mana to deal magic damage + apply debuff
3. **Use Potion** - Restore health or mana
4. **Change Equipment** - Swap weapons/armor mid-battle
5. **Show Info** - View combat stats

#### Monster Turn

Monsters attack the weakest hero with variadic damage (±20% randomness):

- Can **dodge** attacks (based on dodge chance)
- Apply **status effects** from spells
- Use **special abilities** (Dragons breathe fire, Exoskeletons harden)

### Leveling & Progression

```
Experience to Level Up = hero_current_level × 10

Upon Level Up:
├─ Health & Mana: +100% of level scaling
├─ Strength/Dexterity/Agility: Increased per class
└─ New spells unlocked (Sorcerer/Paladin)
```

---

## 👥 Characters

### 🗡️ Heroes

#### Warrior

**Specialization**: Physical Damage & Durability

```
Stat Priority: Strength > Agility > Dexterity
Special Bonus:
  ├─ +10% Attack Damage
  ├─ +5% Strength & Agility on level-up
  └─ Cannot cast spells
```

**Best For**: Aggressive players wanting raw damage output

#### 🔮 Sorcerer

**Specialization**: Magic Damage & Mana Control

```
Stat Priority: Dexterity > Agility > Strength
Special Bonus:
  ├─ +10% Spell Damage
  ├─ +5% Dexterity & Agility on level-up
  ├─ 20% more maximum mana
  └─ Starts with Fireball & Ice Shard
```

**Best For**: Strategic players who manage resources carefully

#### ⚔️ Paladin

**Specialization**: Balanced Hybrid Warrior-Mage

```
Stat Priority: Strength > Dexterity > Agility
Special Bonus:
  ├─ +5% Attack Damage
  ├─ +5% Spell Damage
  ├─ +5% Strength & Dexterity on level-up
  └─ Balanced mana pool
```

**Best For**: Versatile players - jack-of-all-trades approach

### 🐉 Monsters

#### Dragon

**Rare Boss-type Monster**

```
Threat Level: ⭐⭐⭐⭐⭐
Special Ability: Fire Breath (20% trigger)
  ├─ 50% increased damage output
  └─ Devastating ranged attack
Defensive: Low dodge chance (10%)
```

#### 🐛 Exoskeleton

**Tank-type Monster**

```
Threat Level: ⭐⭐⭐
Special Ability: Harden Shell (20% trigger)
  ├─ Reduce incoming damage by additional 20%
  └─ High defense stat
Defensive: Medium dodge chance (15%), 20% increased defense
```

#### 👻 Spirit

**Evasive-type Monster**

```
Threat Level: ⭐⭐
Special Ability: Enhanced Dodge
  ├─ Base dodge: 0.3% + 10% additional chance
  └─ Can dodge multiple times per turn
Defensive: Multiple dodge chances, low defense
```

---

## 🎁 Item System

### Weapons

- **Stat Scaling**: Damage = (weapon_base × (1 + hero_level × 0.05)) + hero_strength
- **Two-Handed Bonus**: +50% damage for two-handed weapons
- **Upgrade Mechanic**: Enhance weapon damage by 10% per upgrade

### Armor

- **Damage Reduction**: Direct reduction from incoming damage
- **Equip System**: One armor piece active at a time
- **Scaling**: Effectiveness irrelevant to level (flat damage reduction)

### Potions

- **Health Potions**: Restore HP (max = hero_max_hp)
- **Mana Potions**: Restore MP (max = hero_max_mp)
- **Single Use**: Consumed after use
- **Scalable Effect**: Higher tier potions heal more

### Spells

- **Mana Cost**: Pay MP to cast (must have sufficient mana)
- **Elemental Types**:
  - 🔥 **Fire**: Reduce monster defense (-10%)
  - ❄️ **Ice**: Reduce monster damage (-10%)
  - ⚡ **Lightning**: Reduce monster dodge chance (-10%)

---

## 🏗️ Architecture & Design Patterns

### Factory Pattern Implementation

The game implements **Factory Pattern** for dynamic character creation:

```
CharacterFactory (Interface)
├── HeroFactory
│   ├─ createcharacter(name, level) → Random Hero
│   └─ createSpecificHero(name, type) → Specific Hero Class
└── MonsterFactory
    ├─ createCharacter(name, level) → Random Monster
    └─ createSpecificMonster(name, type, level) → Specific Monster Class
```

**Benefits**:

- ✅ Decouples client code from concrete character classes
- ✅ Centralized character creation logic
- ✅ Easy to add new hero/monster types
- ✅ Supports both random and specific character generation

### Inheritance Hierarchy

```
GameCharacter (Abstract Base)
├── Hero (Abstract Combat Unit)
│   ├─ Warrior
│   ├─ Sorcerer
│   └─ Paladin
└── Monster (Abstract Magical Creature)
    ├─ Dragon
    ├─ Exoskeleton
    └─ Spirit

Item (Abstract Equipment)
├─ Weapon
├─ Armor
├─ Potion
└─ Spell
```

### Key Design Principles

| Principle                       | Implementation                                                       |
| ------------------------------- | -------------------------------------------------------------------- |
| **Encapsulation**               | Protected attributes with public getters; stat calculations internal |
| **Polymorphism**                | Abstract methods `calculateStats()`, `attack()` overridden per class |
| **DRY (Don't Repeat Yourself)** | Shared logic in `GameCharacter`, specialized in subclasses           |
| **Single Responsibility**       | Each class has one clear purpose (Combat, Items, Spaces)             |
| **Dependency Inversion**        | Factories depend on abstract interfaces, not concrete classes        |

---

## 📁 Project Structure

```
src/com/company/
├── Main.java                          # Entry point
├── Game.java                          # Game orchestrator & main controller
├── World.java                         # 8×8 grid management, space generation
├── Battle.java                        # Turn-based combat engine (379 lines!)
├── Market.java                        # Item trading system
├── Position.java                      # 2D coordinate system
├── InputHandler.java                  # Generic input validation & error handling
│
├── Characters/
│   ├── GameCharacter.java             # Abstract base class (HP, Level, Name)
│   ├── Heroes/
│   │   ├── Hero.java                  # Abstract hero (247 lines - full system)
│   │   ├── Warrior.java               # Physical DPS specialist
│   │   ├── Sorcerer.java              # Magic DPS specialist
│   │   └── Paladin.java               # Hybrid warrior-mage
│   │
│   └── Monsters/
│       ├── Monster.java               # Abstract creature (attack, defense, dodge)
│       ├── Dragon.java                # Fire-breathing boss
│       ├── Exoskeleton.java           # Tank-type defender
│       └── Spirit.java                # Evasion-focused nomad
│
├── Factory/
│   ├── CharacterFactory.java          # Interface pattern
│   ├── HeroFactory.java               # Hero creation logic
│   └── MonsterFactory.java            # Monster creation logic
│
├── Items/
│   ├── Item.java                      # Abstract item base
│   ├── Weapon.java                    # Damage dealers with stat scaling
│   ├── Armor.java                     # Damage reduction
│   ├── Potion.java                    # Consumable health/mana restoration
│   └── Spell.java                     # Magical attacks with debuff effects
│
└── Spaces/
    ├── Space.java                     # Abstract grid cell
    ├── CommonSpace.java               # Traversable area (monster spawn zone)
    ├── MarketSpace.java               # Safe trading area
    └── InaccessibleSpace.java         # Blocked terrain
```

**LOC (Lines of Code) Breakdown**:

- `Hero.java`: 247 lines - Core hero system with inventory, equipment, progression
- `Battle.java`: 379 lines - Complex turn-based combat logic
- `Game.java`: 241 lines - Game flow control and coordination
- Total: ~1,600+ lines of production-quality code

---

## 🚀 Getting Started

### Prerequisites

- **Java 8+** (17 recommended)
- **Maven or Gradle** (optional, project is compilable with javac)
- Terminal/Command Prompt

### Installation

```bash
# Clone the repository
git clone https://github.com/yourusername/monsters-heroes-battle-game.git
cd monsters-heroes-battle-game

# Compile Java files
javac -d out/production/project src/com/company/**/*.java

# Or use your IDE (IntelliJ IDEA recommended)
# File → Open → Select project folder
# Run com.company.Main
```

### Quick Start

```bash
# Option 1: Run from terminal
cd src
javac com/company/**/*.java
java com.company.Main

# Option 2: IDE (Recommended)
# Right-click Main.java → Run 'Main.main()'
```

---

## 🎮 How to Play

### Game Flow

```
1. Enter Party Size (1-3 heroes)
   ↓
2. Create each hero (name + choose class)
   ↓
3. Heroes spawn at random positions on 8×8 grid
   ↓
4. Loop:
   └─ Move heroes with W/A/S/D
   └─ Check current space:
      ├─ Common Space → Random monster encounter
      ├─ Market Space → Trading interface
      └─ Inaccessible Space → Cannot enter
   └─ Handle battles or trades
   └─ Repeat until heroes defeated or victory conditions met
```

### Controls

| Input | Action                                   |
| ----- | ---------------------------------------- |
| **W** | Move UP                                  |
| **A** | Move LEFT                                |
| **S** | Move DOWN                                |
| **D** | Move RIGHT                               |
| **I** | Show hero info (HP, MP, Equipment, Gold) |
| **M** | Enter market (Buy/Sell items)            |
| **Q** | Quit game                                |

### Battle Interface

```
Battle starts!

Hero Turn:
  1. Attack          [Physical damage, uses weapon]
  2. Cast Spell      [Magic damage, costs mana, applies debuff]
  3. Use Potion      [Restore HP/MP]
  4. Change Equipment [Swap weapons/armor]
  5. Show Info       [View battle stats]

Monster Turn:
  [Automatic attack phase]
  └─ Attack pattern varies by monster type
```

### Winning Conditions

- ✅ Defeat all monsters in encounters
- ✅ Survive to complete a full exploration loop
- ✅ Reach higher levels and improve equipment
- ✅ Accumulate gold for powerful items

---

## 🔍 Codebase Analysis

### Code Quality Metrics

| Metric                 | Value                       | Status                 |
| ---------------------- | --------------------------- | ---------------------- |
| **Total Classes**      | 25                          | ✅ Well-Organized      |
| **Design Patterns**    | 2+ (Factory, Strategy)      | ✅ Strong Architecture |
| **Inheritance Depth**  | 3 levels                    | ✅ Appropriate         |
| **Abstraction**        | 5 abstract classes          | ✅ Good Encapsulation  |
| **Exception Handling** | Input Validation, Try-Catch | ✅ Robust              |

### Key Implementation Highlights

#### 1. **Generic Input Handler**

```java
InputHandler<Integer> inputHandler = new InputHandler<>(Integer.class, scanner);
int partySize = inputHandler.getInput("How many heroes? (1-3): ", 1, 3);
```

- Type-safe input validation
- Custom parser support
- Range validation built-in

#### 2. **Polymorphic Combat System**

```java
// Runtime dispatch based on actual hero type
hero.attackDamage() → Warrior: +10%, Sorcerer: standard, Paladin: +5%
hero.castSpell()    → Sorcerer: +10%, Others: standard, Warrior: unsupported
```

#### 3. **Dynamic Stat Scaling**

```java
// Weapon damage scales with hero level AND strength
int scaledDamage = (strength + weaponBase * (1 + heroLevel * 0.05)) * 0.05
```

#### 4. **Procedural World Generation**

```java
// 20% Inaccessible, 30% Market, 50% Common
double chance = random.nextDouble();
if (chance < 0.2) grid[i][j] = new InaccessibleSpace();
```

### Code Complexity Analysis

#### Most Complex Classes (by responsibility)

1. **Hero.java** (247 lines)
   - 📊 Manages: HP, MP, Level, Experience, Gold, Inventory, Equipment
   - Methods: 15+ public methods
   - Interactions: World, Items, Battle, Market

2. **Battle.java** (379 lines)
   - 📊 Manages: Turn sequence, Action resolution, Combat flow
   - Complex logic: Damage calculation, Mana cost verification, Status effects
   - Methods: 20+ private methods handling specific combat phases

3. **Game.java** (241 lines)
   - 📊 Orchestrates: Game initialization, Input handling, Game loop
   - Coordinates: World, Heroes, Battle, Market, InputHandler

#### Code Patterns & Best Practices

✅ **Defensive Programming**

```java
public void takeDamage(int damage) {
    hp = Math.max(0, hp - damage);  // Prevent negative HP
}

public void setHp(int hp) {
    this.hp = Math.min(hp, maxHp);  // Prevent over-heal
}
```

✅ **Null Safety & Boundary Checks**

```java
if (hero.getPosition().x == x && hero.getPosition().y == y) {
    // Safe position comparison
}

if (choice > 0 && choice <= items.size()) {
    // Prevent index out of bounds
}
```

✅ **Polymorphic Dispatch**

```java
// Works with any Hero subclass
Hero hero = ((HeroFactory) heroFactory).createSpecificHero(name, type);
```

### Design Patterns Used

#### 1. Factory Pattern ⭐⭐⭐

**Location**: `Factory/` package

```
HeroFactory & MonsterFactory implement CharacterFactory
- Centralizes object creation
- Supports random & specific instantiation
- Easy to extend with new types
```

#### 2. Strategy Pattern ⭐⭐

**Location**: `Characters/` inheritance hierarchy

```
Each hero/monster class implements unique:
- calculateStats() strategy
- attack() strategy
- levelUp() strategy
This allows runtime algorithm selection
```

#### 3. Abstract Factory (implicit) ⭐

**Location**: `Items/` hierarchy

```
Item abstract class with subclasses:
- Weapon, Armor, Potion, Spell
Each with unique use() behavior
```

#### 4. Template Method ⭐

**Location**: `Hero.levelUp()` & `Monster.takeDamage()`

```
Base class defines skeleton, subclasses fill in details
GameCharacter.levelUp() → Hero.levelUp() → Warrior.levelUp()
```

### Potential Extensions

The codebase architecture supports easy extension:

```java
// Adding new hero class:
public class Barbarian extends Hero {
    @Override
    protected void calculateStats() { /* ... */ }
    @Override
    public int getAttackDamage() { /* ... */ }
}

// Adding new monster:
public class Wraith extends Monster {
    @Override
    protected void calculateStats() { /* ... */ }
    @Override
    public int attack() { /* ... */ }
}

// New item types:
public class Ring extends Item {
    @Override
    public void use(Hero hero) { /* ... */ }
}
```

### Technical Debt & Improvements

| Issue          | Current                    | Improvement                   |
| -------------- | -------------------------- | ----------------------------- |
| Input Handling | Multiple Scanner instances | Use singleton pattern         |
| Randomness     | Math.random() scattered    | Seed-based Random for testing |
| Market         | Static item generation     | Load from config files        |
| Persistence    | No save/load               | Serialization support         |
| Logging        | println() everywhere       | Logger framework (Log4J)      |

---

## 📊 Class Diagram

```
                     ┌─────────────────────┐
                     │  GameCharacter      │
                     │  (Abstract)         │
                     ├─────────────────────┤
                     │ # name: String      │
                     │ # level: int        │
                     │ # hp: int           │
                     │ # maxHp: int        │
                     ├─────────────────────┤
                     │ + getLevel()        │
                     │ + getHp()           │
                     │ + takeDamage()      │
                     │ + levelUp()         │
                     └──────────┬──────────┘
                                │
                   ┌────────────┴────────────┐
                   ▼                         ▼
            ┌──────────────┐        ┌────────────────┐
            │    Hero      │        │    Monster     │
            │ (Abstract)   │        │  (Abstract)    │
            ├──────────────┤        ├────────────────┤
            │ # mp: int    │        │ # defense: int │
            │ # gold: int  │        │ # dodge: %     │
            │ # inventory[]│        ├────────────────┤
            ├──────────────┤        │ + attack()     │
            │ + buyItem()  │        │ + defend()     │
            │ + levelUp()  │        │ + dodge()      │
            └────┬─────────┘        └────────────────┘
                 │                   │         │
        ┌────────┼────────┐         │         │
        ▼        ▼        ▼         ▼         ▼
    ┌───────┐┌────────┐┌────────┐┌─────┐┌──────────┐
    │Warrior││Sorcerer││Paladin ││Dragon││Exoskel. │
    └───────┘└────────┘└────────┘└─────┘└──────────┘
                                         │
                                         ▼
                                    ┌────────┐
                                    │ Spirit │
                                    └────────┘
```

---

## 📈 Statistics

### Project Scope

```
Total Java Files:         25 classes
Total Lines of Code:      ~1,600+ LOC
Main Classes:             7 (Game, World, Battle, Market, etc.)
Character Classes:        10 (3 Hero types + 3 Monster types + base classes)
Item Classes:             5 (Weapon, Armor, Potion, Spell, base)
Space Types:              4 (Common, Market, Inaccessible, base)
Factory Classes:          3 (Character, Hero, Monster factories)
```

### Complexity Distribution

```
Battle.java               ████████████░ 379 lines (Complex combat logic)
Hero.java                ██████████░░░ 247 lines (Hero system)
Game.java                ██████████░░░ 241 lines (Game loop)
Monster.java             ████████░░░░░ 178 lines (Monster behavior)
Market.java              ████████░░░░░ 106 lines (Trading)
World.java               ████████░░░░░ 106 lines (Grid management)
InputHandler.java        █████░░░░░░░░  112 lines (Validation)
```

---

## 🛠️ Technical Stack

| Component           | Technology                                   |
| ------------------- | -------------------------------------------- |
| **Language**        | Java 8+ (Object-Oriented)                    |
| **Build Tool**      | Maven / Gradle (Optional)                    |
| **IDE**             | IntelliJ IDEA, Eclipse, VS Code              |
| **Design Patterns** | Factory, Strategy, Template Method           |
| **Architecture**    | Layered (Game → World → Battle → Characters) |
| **Testing**         | Manual gameplay testing                      |

---

## 📝 Gameplay Example

```
=== MONSTERS AND HEROES ===

How many heroes in your party? (1-3): 2

Enter hero name: Aragorn
Choose hero type (Warrior/Sorcerer/Paladin): Warrior

Enter hero name: Gandalf
Choose hero type (Warrior/Sorcerer/Paladin): Sorcerer

[World Grid Display]
AR  .   .   MA  .   .   .   .
.   .   .   .   .   AR  .   .
MA  .   AR  .   .   .   .   .
...

Enter move (W/A/S/D) or action (I/M/Q): W

[Monsters encountered in common space]
Battle starts!

Aragorn's turn:
1. Attack
2. Use Potion
3. Change Equipment/Armor
4. Show Info
Choose action: 1

Aragorn attacks Dragon for 45 damage!
Dragon HP: 134/179

Dragon attacks Aragorn for 38 damage!
Aragorn HP: 62/150

[Battle continues...]
```

---

## 👨‍💼 Author

**Saleeq Adnan Syed**

- 📧 Email: Saleeq@bu.edu
- 🎓 Student ID: U34331475
- 🏫 Institution: Boston University (BU)
- 📚 Course: CS611 - Advanced Programming Languages
- 📋 Assignment: #4 - Monsters and Heroes

---

## 📜 License

This project is created for educational purposes as part of Boston University's CS611 course. All rights reserved by the author.

---

## 🎯 Key Takeaways

This project demonstrates:

✅ **Object-Oriented Programming** - Inheritance, polymorphism, encapsulation
✅ **Design Patterns** - Factory, Strategy, Template Method patterns
✅ **System Architecture** - Well-layered, decoupled component design
✅ **Combat Systems** - Complex turn-based mechanics with special abilities
✅ **Game Loop** - Professional game flow control and state management
✅ **Input Validation** - Robust error handling and user input processing
✅ **Code Organization** - Clean, maintainable structure with clear separation of concerns

---

## 🚀 Future Enhancements

Potential features for expansion:

- 🗺️ Larger, more diverse world maps
- 👥 Multiplayer support (network battles)
- 💾 Save/Load game states
- 🎨 GUI implementation (JavaFX/Swing)
- 📊 Statistics & achievement system
- 🎵 Sound effects and music
- 🧙 More hero/monster classes
- ⚙️ Configuration file system
- 🤖 AI opponent system
- 📱 Mobile port (using game engine)

---

<div align="center">

### ⭐ If you find this project interesting, please star it! ⭐

**Happy Gaming!** 🎮

</div>
