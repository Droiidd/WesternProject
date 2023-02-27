# WesternProject

A western themed minecraft spigot project for 1.19.2.

> ### Main Overview
>
>An open world western gunslinger. A new found world, taken swiftly from it's native people. Survive amidst the chaos of an ever expanding world. Spend your time exploring the mountaintops for valuable resources. Become a bandit and raid scrap encampments. Join up with a crew to take out high loot areas like oil field, or the citadel. Help to stop the growing rise of crime by becoming a sheriff. Climb the ranks by selling wanted player heads. Explore a world full of resources. Survive, journey through and make the choice of your story. Be good or be evil. something something., (flesh out as we actually add these features)

## Current Features

- [**Teams**](#teams)
- [**Economy**](#economy)
- [**Safe Zones**](#safe-zones)
- [**Wanted / bounties**](#wanted--bounty)
- [**Sheriff**](#sheriff-roles)

## Teams 
> ### Overview
>
>- Teams have a capacity of **6 players** currently.
>- Players in the same team cannot damage each other, and have optional teleporting

### [**Back to the top**](#current-features)

### Command List 

``/team create {teamName}``

``/team leave`` -> (Last to leave disbands)

``/team invite {player}``

``/team accept`` -> Accept an invite

``/team info`` -> Displayes team info

``/team info {player}`` -> Displays other players team info

``/team help``

``/team list`` -> Lists all teams

---

### **WIP Team Features**

- Players within the same team cannot hurt each other
- Add team ranks (team owner, captain and member)
    - Owners control ranks and FF
    - Captains can invite / kick
    - Members have no perms
- If you're in the same team you can tp to each other
- /team delete removes all players from team, then deletes it
- Need to add a char max cap for names
- Need to make it only accept Char for name, no symbols

## Economy

> ### Overview
>
> Players will have access to two types of currency, a wallet and a bank account. 
> On player death, they lose all money in their wallet. This will be dropped as a physical item that other players can then pick up. Players can optionally store this money inside a bank account, thus avoiding loss on death. See [Economy Commands](#economy-commands). Bank and wallet funds are displayed on the players scoreboard.

### [**Back to the top**](#current-features)

### Economy Commands

``/wallet`` -> Allows you to view the gold in your wallet.

``/balance`` **or** ``/bal`` 

``/balance {player}`` **or** ``/bal {player}`` -> View another players funds

``/dropmoney {amount}``

``/dm {amount}`` -> Quicker drop money

``/givegold {player} {amount}`` -> Admin command for spawning gold

### **Features planned**

---
- [ ] Fix picking up gold for money
- [ ] Add banking NPC's for all deposit/withdrawal activity

## Safe Zones

> ### Overview
> 
> Walking into different zones will grant a player certain immunites. If a player goes into a specified
> safe zone, they will not be able to be harmed. If it is stated as **wilderness** or **pvp**, players can 
> attack one another.

### [**Back to the top**](#current-features)

### Color guide

- Blue : Safe zone
- Green : Wilderness (PVP)
- Red : Illegal PVP zone
- Gold : Special areas
### **Features planned**

---
- [ ] Players in specified safe zones are not harmed
- [ ] Players are displayed a message when entering and leaving zones
- [ ] Illegal areas slowly increase your bounty
 
## Wanted / Bounty

> ### Overview
> 
> Players can become "wanted" by attacking any other **non wanted** player. If a player attacks a sheriff,
> they too will become wanted. Wanted players will eventually be able to:
> - Be tracked by other players
> 
> **Current features**:
> - If killed, will drop a higher cut of their bounty:
>   - player_bounty = 500, head_value = 250 (50%)
>   - Any **non wanted** player head is 5% of their bounties value
> - If they attack a player, the attackers bounty will raise
> - If they kill a player, their bounty increases significantly
> - If a player has a bounty on death, they will face jail time
> 
> All of this wanted information and timers can be seen on a player's scoreboard. To view
> online wanted players, simply hold {tab}, or the default key for list

### [**Back to the top**](#current-features)

## Sheriff roles

> ### Overview
> 
> Sheriff roles are roles any player can choose to take on in counterpart to a bandit. By default,
> a player is assigned the bandit role. The player is free at any point to change this at the sheriff
> department. Here there will be an NPC that gives you the option of **recruitment** or **retirement**.
> 
> **Recruitment**:
> - When choosing this option, the player will lose **all** items, other than currency.
> - The player will also automatically be kicked from any team, to avoid having bandit/sheriff teams.
>   - You can essentially consider this a complete stat reset, in exchange a different kind of play style
> 
> **Retirement**:
> - As the name suggests, upon choosing this you will no longer be a sheriff.
> - Similar to recruitment, all items and team affiliations are lost on retirement. 
>     - Again to avoid non sheriff and non bandit items
> 
> A sheriff has access to a single command listed [here](#sheriff-commands). This will teleport them to
> the sheriff headquarters. Here sheriffs can rank up and purchase special sheriff only equipment.
> This is the only location on the map that you can find these NPC's. This includes:
> 
> - ```Sheriff arms dealer``` -> Sells sheriff specific weapons **only**
> - ```Sheriff armorer``` -> Sells upgraded armor 
> - ```Chief of police``` -> Ranks a sheriff up
> 
> Sheriffs can give the Chief wanted player heads in exchange for points. When the sheriff reaches a certain
> amount of points, that sheriff can optionally rank up at the ```chief of police```. **Any** sheriff can purchase **any** sheriff weapon.
> However, only a sheriff of a certain rank, can purchase more durable armor.
> 
> Sheriff's naturally spawn with plain iron armor and a pistol, however they have a longer death timer than bandits.
> Sheriff's cannot commit illegal activities or use illegal weapons.

### [**Back to the top**](#current-features)

### Sheriff commands

---

```/role set {player} sheriff``` -> makes the player a sheriff

```/role remove {player} sheriff``` -> removes the player from the role

```/sheriffhq``` -> teleports a player to the sheriff hq

### **Features planned**

There's a lot to flesh out as we add more features to the game.

- [ ] Multiple sheriff ranks
- [ ] Recruit and retire at an NPC
- [ ] Specific sheriff zone / hq
- [ ] Sheriffs cannot hurt one another
- [ ] Chief head collector system

## Foraging

> **Overview**
> 
> All forage-ables can be sold for a decent price to an NPC within the witch cove. (Potential required
> crafting item from witch cove -> potions). Foragables were added to the game for an interesting side hustle,
> and to remove the benefits from purchasable ranks. In addition to being sellable, each foragable can be used in
> crafting AND are pretty much exclusive crafting items. Meaning, they are the only items in the game that can be
> used in crafting.
> 
> At the [NPC List](#NPC) you can find two NPC's related to foraging. One who sells recipes, and one who
> sells locations. Recipes are a required item in each crafting recipe, additionally telling you the
> ingredients.
> 
> Foragables can be found all over the map, but usually find themselves a home in certain areas. Locations
> are a piece of paper that can guide you to specific forgables: "Mist wood, found near bodies of water" for example.
### Potion Recipes

### Intended features
- [ ] Add crafting recipes
- [ ] Add specific ingredients found in high danger areas
- [ ] Come up with locations for forgables
- [ ] Add location papers

## Mining
