## RSVega - Automation for Old School RuneScape® bot farms/account checking

### What is RSVega?
RSVega is a project that aims to fully automate bot farms and account checking for [Old School RuneScape®](https://oldschool.runescape.com/) built upon the [RSPeer](https://rspeer.org/) platform.

Note: RSVega contains unrelated [SPX] scripts for the RSPeer community such as [SPX] Tutorial Island. These do not interfere with RSVega and are simply included for the RSPeer community.

### How does it work?
RSVega uses a mission/worker based framework in order to write and queue scripts that are ran on the RSPeer bot client. The RSVega script is the main entry-point that determines which mission to execute and when. Each mission controls specific entities of the RSVega system such as account creation, tutorial island, muling and much more.

For now, RSVega bot sessions are controlled via the [RSPeer Bot Panel](https://app.rspeer.org/) where they can be started, stopped, modified, and more.

### Repositories
[RSVega-scripts](https://github.com/Sphiinx/RSVega-scripts) - This repository contains the main-entry points for the RSVega scripts.

[RSVega-scriptapi](https://github.com/Sphiinx/RSVega-scriptapi) - This repository contains the library of methods for the RSVega scripts to use.

[RSVega-restapi](https://github.com/Sphiinx/RSVega-restapi) - This repository contains the RESTful api for controlling the back-end system of RSVega.

[RSVega-discord-bot](https://github.com/Sphiinx/RSVega-discord-bot) - This repository contains the RSVega discord bot.

### Current support
- Account creation
- Bot, stats, mule, and session data collection
- Buying/Selling at the Grand Exchange
- Giving/Receiving items from a mule
- Tutorial Island mission
- AIO Firemaking mission
- AIO Questing mission

### Project board
Check the [RSVega Project Board](https://github.com/users/Sphiinx/projects/1) for to-do and in-progress tasks.
