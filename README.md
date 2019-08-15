# TimeJoin

**TimeJoin** is a simple solution to putting your server into a "closed" state automatically. 

Whether you want your server to be whitelisted during a certain time, or just closed to players overnight, this plugin is for you.

The config is pretty straight forward, just plop in your servers "open" hours and players will be able to join during this time, and not during your closed time. 

You can also configure the timer. This timer will run after your closed time, give players a heads up the server will be closing, then after the configurable time it will kick the players!

**VERSIONS:**   
I tested this on 1.14.4, but I see no reason it shouldn't work on any Spigot version, there's really nothing version specific in here. That said, I will only offer support for servers running 1.12.2+

**COMMANDS:**   
/timejoin reload: Reload data from the config

**PERMISSIONS:**   
- timejoin.canjoin = If the player has this permission they will be able to join your server during its closed period.
- timejoin.command = Allows a player to reload the config.

Check it out on [**SpigotMC**](https://www.spigotmc.org/resources/timejoin.70385/)
