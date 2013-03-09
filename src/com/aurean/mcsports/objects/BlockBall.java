package com.aurean.mcsports.objects;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import com.aurean.mcsports.Game;

public class BlockBall extends Ball {
	Block ballBlock;
	private int id;
	private byte data;
	private Game game;
	private World world;
	public BlockBall(String name, Block block, Game game) {
		super(name, block.getLocation());
		this.ballBlock = block;
		this.id = ballBlock.getTypeId();
		this.data = ballBlock.getData();
		this.game = game;
		this.world = ballBlock.getWorld();
	}
	
	public int getId(){
		return id;
	}
	public byte getData(){
		return data;
	}

	@Override
	public Location getLocation() {
		return ballBlock.getLocation();
	}

	@Override
	public void kick(Vector v) {
		Location newBlock = ballBlock.getLocation().toVector().add(v).toLocation(world);
		if (ballBlock.getLocation().equals(newBlock)) return; //If the ball didnt move, return.
		
		//TODO add ingoal check
		if (world.getBlockAt(newBlock).getTypeId() != 0){
			//Ball hit something that is not air
			//Should now bounce to the closest air
			newBlock = getClosestAir(newBlock);
		}
		GameField field = game.getField();
		if (!field.isInField(newBlock)){
			//Out of bounds
			int blockX = newBlock.getBlockX();
			int blockZ = newBlock.getBlockZ();
			if (blockX>field.xLargest) newBlock.setX(field.xLargest);
			else if (blockX<field.xSmallest) newBlock.setX(field.xSmallest);
			if (blockZ>field.zLargest) newBlock.setZ(field.zLargest);
			else if (blockZ<field.zSmallest) newBlock.setZ(field.zSmallest);
			//Set the ball's location to the closest fieldborder.
		}
		//With this setup, it can occur that if the border of the field is not air, the ball is set to a block
		//that was not air. This makes no-out-of-bounds kinda impossible, as it will be impossible to get the ball
		//out of the border again.
		
		setBallObjectLocation(newBlock);
	}
	
	@Override
	public void setBallObjectLocation(Location l){
		world.getBlockAt(l).setTypeId(id);
		world.getBlockAt(l).setData(data);
		ballBlock.setTypeId(0);
		ballBlock = world.getBlockAt(l);
	}

	@Override
	public void fix() {
		ballBlock.setTypeId(id);
		ballBlock.setData(data);
		//Set the ball to the current id and data.
		//(Fix never really has to be called besides from the reset.)
	}

	@Override
	public void reset() {
		ballBlock.setTypeId(0);//Remove the ball
		ballBlock = world.getBlockAt(getSource()); //Set the ball location back to the starting position.
		fix(); //Make a new ball at that location.
	}

}
