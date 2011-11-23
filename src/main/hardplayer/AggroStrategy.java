package hardplayer;

import battlecode.common.RobotController;

class AggroStrategy extends Static implements Strategy {

	public void execute(RobotController myRC) {
		while(true) {
			try {
				switch(myRC.getType()) {
					case ARCHON:
						new AggroArchonPlayer(myRC).run();
						new ArchonPlayer(myRC).run();
						break;
					case SOLDIER:
						new AggroSoldierPlayer(myRC).run();
						break;
					case SCOUT:
						new ScoutPlayer(myRC).run();
						break;
					case SCORCHER:
						new ScorcherPlayer(myRC).run();
						break;
					default:
						debug_println("I don't know what kind of robot I am!");
						myRC.suicide();
				}
			} catch(Exception e) { debug_stackTrace(e); }
			myRC.yield();
		}
	
	}

}
