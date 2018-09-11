# 20 Day Hero Specification

20 Day Hero is a text based RPG which is played directly from the command line.  In the game, a witch has put a curse on many of the great heros of the land.  The curse gives the heros 20 days to live.  You start the game as one of these heros and must find and defeat the witch in this time frame to break the curse.  If the curse is broken you win the game.  If you die, you begin the game as a different hero of the same land starting on the same start day. Thus you could run into old enemies you have battled in the past or even your old hero! 

Your character has several stats including an attack stat, a defense stat and an immunity stat (used in battle). The Character also has a remaining life stat which starts at 20 and is decremented every turn. Heros start with 10 attack, 10 defense and 0 immunity

Each turn counts as one day and a turn has two phases. In the first phase you are in a town.  In the town you have options of choosing a single place to visit and performing a single option there.  The effects of the different options are shown below:

- Place(Armory) Option(Get 10 Defense): Hero gains 10 defense
- Place(Blacksmith) Option(Get 4 Offense and 4 Defense): Hero gains 4 attack and 4 defense
- Place(Potion Shop) Option(Buy Life Potion): In exchange for 10 attack, the hero gets an extra five days to live
- Place(Potion Shop) Option(Buy Immunity Potion): In exchange for 10 attack, the hero gets one additional immunity stat
- Place(Festival) Option(Bet All Stats): The user gambles all their stats where payout is double, approximately 47% chance of winning
- Place(Festival) Option(Bet Half Stats): The user gambles half their stats where payout is double, approximately 47% chance of winning
- Place(Festival) Option(Bet Quarter Stats): The user gambles a quarter of their stats where payout is double, approximately 47% chance of winning
- Place(Graveyard) Option(Mourn Dead): Hero gains 1 attack and 1 defense for every 50 characters(hero or NPC) which have died during the current heros run
- Place(Graveyard) Option(Pray): Hero gains 1 attack and 1 defense for each previous game death they have had
- Option(Leave): Nothing happens

After completing the selected option, the travel phase begins. The map is a 10 by 10 grid of different towns. The user can travel north, south, east or west (more restricted at grid boundary). When traveling the user can encounter enemies and have to battle. Each town summons four NPCs (one in each direction) every turn.  Each NPC has a 2/3 chance of being an enemy and start with between 5 and 7 attack and defense.  In a battle the good and bad teams fight in which individual characters duel. In the duel both Characters defense are decremented by the opposites offense. If defense is below zero the character dies. The two sides fight until one is completely defeated. A hero can be revived if they have an immunity stat, which afterwards is decremented. Survivors of the battle continue to move around, NPCs gain plus 2 attack and defense. As some NPC are good and some bad, NPC can battle each other in a different location than the user hero. The goal of traveling is to find The City of Witches, where at the Witch Hut place the Witch can be fought. The Witch has between 80 and 100 attack and defense so building up your own stats is important. If you fight the witch on the same day a previous one of your heros faught her, you will fight her together.  If she dies you win the game.
