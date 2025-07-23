# Fantasy Battle Arena Game

A text-based fantasy strategy game where players can create armies, purchase equipment, and battle against other players in different terrains.

## 🎮 Game Overview

This is a turn-based strategy game where players:
- Create and manage character profiles
- Build armies with different character types
- Equip characters with armor and artifacts
- Battle against other players in various terrains
- Earn experience points and gold coins through victories

## 🏗️ Project Structure

```
pc_project/
├── demo.java              # Main game file with core logic
├── addUserProfile.java    # User profile management
├── charactors.java        # Character classes and definitions
├── player.java           # Player class implementation
├── vepons.java           # Weapons and equipment
├── war.java              # Battle system implementation
├── restore.java          # Game state restoration
├── test.xml              # Test configuration
├── pc_project.jar        # Compiled JAR file
├── pcProject.exe         # Executable file
└── *.class files         # Compiled Java classes
```

## 🚀 How to Run

### Option 1: Using Java Command Line
```bash
# Navigate to the project directory
cd "d:\2nd sem\PC\pc_project"

# Compile the Java file
javac demo.java

# Run the game
java demo
```

### Option 2: Using the JAR file
```bash
java -jar pc_project.jar
```

### Option 3: Using the Executable
```bash
./pcProject.exe
```

## 🎯 Game Features

### Character Types
1. **Archers** 🏹
   - Shooter (80 gc)
   - Ranger (115 gc)
   - Sunfire (160 gc)
   - Zing (200 gc)
   - Saggitarius (230 gc)

2. **Knights** ⚔️
   - Squire (85 gc)
   - Cavalier (110 gc)
   - Templar (155 gc)
   - Zoro (180 gc)
   - Swiftblade (250 gc)

3. **Mages** 🔮
   - Warlock (100 gc)
   - Illusionist (120 gc)
   - Enchanter (160 gc)
   - Conjurer (195 gc)
   - Eldritch (270 gc)

4. **Healers** 💚
   - Soother (95 gc)
   - Medic (125 gc)
   - Alchemist (150 gc)
   - Saint (200 gc)
   - Lightbringer (260 gc)

5. **Mythical Creatures** 🐉
   - Dragon (120 gc)
   - Basilisk (165 gc)
   - Hydra (205 gc)
   - Phoenix (275 gc)
   - Pegasus (340 gc)

### Equipment

#### Armor Types
- **Chainmail** (70 gc) - +1 Defence, -1 Speed
- **Regalia** (105 gc) - +1 Defence
- **Fleece** (150 gc) - +2 Defence, +1 Health, -1 Speed

#### Artifacts
- **Excalibur** (150 gc) - +2 Attack
- **Amulet** (200 gc) - -1 Defence, +1 Health, +1 Speed, +1 Attack
- **Crystal** (210 gc) - +1 Defence, -1 Health, -1 Speed, +2 Attack

### Battle Grounds
- **Hillcrest** - Advantage for Hillcrest natives
- **Marshland** - Advantage for Marshland natives
- **Desert** - Advantage for Desert natives
- **Arcane** - Special bonuses for Arcane natives

## 🎮 How to Play

1. **Create User Profile**
   - Enter your name and username
   - Choose your home ground
   - Start with 500 gold coins

2. **Build Your Army**
   - Purchase characters from different types
   - Each player can have one character from each type
   - Manage your gold coins wisely

3. **Equip Your Characters**
   - Buy armor to increase defence
   - Purchase artifacts to boost various stats
   - Each character can have one armor and one artifact

4. **Battle System**
   - Challenge other players to battle
   - Battles take place on the opponent's home ground
   - Win battles to gain XP and gold coins
   - Lose battles and forfeit some gold coins

5. **Character Management**
   - Sell characters for 90% of their current value
   - Replace equipment (lose the old one)
   - Restore characters after battles

## 🏆 Game Mechanics

### Combat System
- Turn-based combat with speed determining attack order
- Attack priority: Archers > Knights > Mythical Creatures > Mages > Healers
- Defence priority: Healers > Knights > Mages > Mythical Creatures > Archers
- Terrain bonuses based on character's homeland

### Economy
- Starting gold: 500 coins
- Equipment cost: 20% of item price affects character value
- Battle rewards: 10% of opponent's gold coins
- Character selling: 90% of current character value

### Character Stats
Each character has:
- **Attack** - Damage dealt in combat
- **Defence** - Damage reduction
- **Health** - Hit points
- **Speed** - Turn order in battle
- **Price** - Gold coin cost and selling value

## 🔧 Technical Requirements

- Java 8 or higher
- Command line interface
- Text-based user interaction

## 🎯 Default Player

The game includes a default player "GeraltofRivia" (username: "whitewolf") with:
- Pre-built army: Ranger, Squire, Warlock, Medic, Dragon
- 485 gold coins (500 - 15)
- 32 XP points
- Marshland as home ground
- Ranger equipped with Chainmail
- Medic equipped with Amulet

## 🔄 Game Flow

1. Main Menu → Create Profile / Change Name / Next / Exit
2. Character Management → Add Characters / Add Armor / Add Artifacts / Sell Characters
3. Battle System → Challenge Players / View Results
4. Post-Battle → Character Restoration / Stat Updates

## 📝 Notes

- Characters are automatically restored to their original state after battles
- Equipment effects are maintained between battles
- Only one character of each type allowed per player
- Battle outcomes affect XP and gold coin distribution
- Terrain advantages provide strategic depth

---

**Enjoy building your ultimate fantasy army and conquering the battlefield!** ⚔️🏆
