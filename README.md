# WesternProject

A western themed minecraft spigot project for 1.19.2.

## Current Features

- [**Teams**](#teams)
- [**Economy**](#economy)
- Safezones
- Wanted / bounties
- Sheriff

## Teams 
> ### Overview
>
>- Teams have a capacity of **6 players** currently.
>- Players in the same team cannot damage each other, and have optional teleporting

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

### Economy Commands

``/wallet`` -> Allows you to view the gold in your wallet.

``/balance`` **or** ``/bal`` 

``/balance {player}`` **or** ``/bal {player}`` -> View another players funds

``/dropmoney {amount}``

``/dm {amount}`` -> Quicker drop money

``/givegold {player} {amount}`` -> Admin command for spawning gold

### **Features planned**

---

- [] gfgf

- [] fgsgfd gdf

- [] 
