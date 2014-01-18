package yourmom;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotType;
import battlecode.common.Team;

public abstract class BaseRobot {
	// Core Subsystems
	public final RobotController rc;
	public final MapCacheSystem mc;
	public final NavigationSystem nav;

	// Robot Statistics - permanent variables
	public final RobotType myType;
	public final Team myTeam;
	public final int myID;
	public final double myMaxHealth;
	public final int birthday;
	public final MapLocation birthplace;

	// Robot Statistics - updated per turn
	public double curHealth;
	public MapLocation curLoc;
	public MapLocation prevLoc;
	public Direction curDir;
	public int curRound;

	// Robot Flags - toggle important behavior changes
	public boolean gameEndNow = false;

	// Internal Statistics
	private int lastResetTime = 50;
	private int executeStartTime = 50;
	private int executeStartByte;

	// Statics
	public static final Direction[] USEFUL_DIRECTIONS = new Direction[] {
		Direction.NORTH,
		Direction.NORTH_EAST,
		Direction.EAST,
		Direction.SOUTH_EAST,
		Direction.SOUTH,
		Direction.SOUTH_WEST,
		Direction.WEST,
		Direction.NORTH_WEST
	};
	public static final int NDIRECTIONS = USEFUL_DIRECTIONS.length;

	public BaseRobot(RobotController myRC) throws GameActionException {
		rc = myRC;

		myType = rc.getType();
		myTeam = rc.getTeam();
		myID = rc.getRobot().getID();
		myMaxHealth = myType.maxHealth;
		birthday = Clock.getRoundNum();
		birthplace = rc.getLocation();
		updateRoundVariables();

		mc = new MapCacheSystem(this);
		nav = new NavigationSystem(this);
	}

	public abstract void run() throws GameActionException;

	public void loop() {
		while (true) {
			// Begin New Turn
			resetClock();
			updateRoundVariables();

			try {
				// Main Run Call
				run();

				// Check if we've already run out of bytecodes
				if (checkClock()) {
					rc.yield();
					continue;
				}

				// TODO Otherwise...wear hat?
			} catch (Exception e) {
				e.printStackTrace();
			}

			rc.yield();
		}
	}

	public void updateRoundVariables() {
		curRound = Clock.getRoundNum();
		curHealth = rc.getHealth();
		prevLoc = curLoc;
		curLoc = rc.getLocation();
		if (curLoc != null && prevLoc != null) {
			curDir = prevLoc.directionTo(curLoc);
		} else {
			curDir = Direction.NORTH;
		}

		gameEndNow = curRound > GameConstants.ROUND_MAX_LIMIT;
	}

	public int getAge() {
		return birthday - curRound;
	}

	/** Resets the internal bytecode counter. */
	public void resetClock() {
		lastResetTime = executeStartTime;
		executeStartTime = Clock.getRoundNum();
		executeStartByte = Clock.getBytecodeNum();
	}

	/** Prints a warning if we ran over bytecodes. 
	 * @return whether we run out of bytecodes this round.
	 */
	private boolean checkClock() {
        if(executeStartTime==Clock.getRoundNum())
        	return false;
        int currRound = Clock.getRoundNum();
        int byteCount = (GameConstants.BYTECODE_LIMIT-executeStartByte) + (currRound-executeStartTime-1) * GameConstants.BYTECODE_LIMIT + Clock.getBytecodeNum();
//        dbg.println('e', "Warning: Over Bytecode @"+executeStartTime+"-"+currRound +":"+ byteCount);
        return true;
	}

	/** Should be overridden by any robot that wants to do movements. 
	 * @return a new MoveInfo structure that either represents a spawn, a move, or a turn
	 */
	// public MoveInfo computeNextMove() throws GameActionException {
	// 	return null;
	// }
	
	/** If there are bytecodes left to use this turn, will call this function
	 * a single time. Function should try very hard not to run over bytecodes.
	 * Overriding functions should make sure to call super.
	 * @throws GameActionException 
	 */
	public void useExtraBytecodes() throws GameActionException {
		
		// Game Ending Detection Stuff
		// if(gameEndDetected && Clock.getRoundNum() == curRound && Clock.getBytecodesLeft() > 300) {
		// 	if(Clock.getRoundNum()%11 == myID%11)  //announce to allies
		// 		io.sendUShort(BroadcastChannel.ALL, BroadcastType.DETECTED_GAME_END, gameEndTime);
		// }
	
		// // Message IO
		// if(Clock.getRoundNum()==curRound && Clock.getBytecodesLeft()>2000 &&
		// 		rc.getFlux() > 0.1) 
		// 	io.sendAll();
	}
	
	public String locationToVectorString(MapLocation loc) {
		if(loc==null) return "<null>";
		return "<"+(loc.x-curLoc.x)+","+(loc.y-curLoc.y)+">";
	}
}