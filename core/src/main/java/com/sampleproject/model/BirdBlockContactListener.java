package com.sampleproject.model;
import com.badlogic.gdx.physics.box2d.*;
import com.sampleproject.model.BirdInterface;
import com.sampleproject.model.Block;

public class BirdBlockContactListener implements ContactListener {


    @Override
    public void beginContact(Contact contact) {

        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        Body bodyA = fixtureA.getBody();
        Body bodyB = fixtureB.getBody();


        if (isBird(fixtureA) && isBlock(fixtureB)) {
            handleBirdBlockCollision(bodyA, bodyB);
        } else if (isBlock(fixtureA) && isBird(fixtureB)) {
            handleBirdBlockCollision(bodyB, bodyA);
        }

        if (isBird(fixtureA) && isRock(fixtureB)) {
            handleBirdRockCollision(bodyA, bodyB);
        } else if (isRock(fixtureA) && isBird(fixtureB)) {
            handleBirdRockCollision(bodyB, bodyA);
        }

        if (isPig(fixtureA) && isBlock(fixtureB)) {
                handlePigBlockCollision(bodyA, bodyB);
        }
        else if (isPig(fixtureB) && isBlock(fixtureA)) {
                handlePigBlockCollision(bodyB, bodyA);
        }


        if (isPig(fixtureA) && isBird(fixtureB)) {
            handleBirdPigCollision(bodyB, bodyA);
        }
        else if (isPig(fixtureB) && isBird(fixtureA)) {
            handleBirdPigCollision(bodyA, bodyB);
        }


        if (isPig(fixtureA) && isRock(fixtureB)) {
            handlePigRockCollision(bodyA, bodyB);
        }
        else if (isPig(fixtureB) && isRock(fixtureA)) {
            handlePigRockCollision(bodyB, bodyA);
        }

        if (isRock(fixtureA) && isGlass(fixtureB)) {
            handleRockGlassCollision(bodyA, bodyB);
        }
        else if (isRock(fixtureB) && isGlass(fixtureA)) {
            handleRockGlassCollision(bodyB, bodyA);
        }

        if (isBlock(fixtureA) && isGlass(fixtureB)) {
            handleBlockGlassCollision(bodyA, bodyB);
        }
        else if (isBlock(fixtureB) && isGlass(fixtureA)) {
            handleBlockGlassCollision(bodyB, bodyA);
        }

        if (isPig(fixtureA) && isGlass(fixtureB)) {
            handlePigGlassCollision(bodyA, bodyB);
        }
        else if (isPig(fixtureB) && isGlass(fixtureA)) {
            handlePigGlassCollision(bodyB, bodyA);
        }

        if (isBird(fixtureA) && isGlass(fixtureB)) {
            handleBirdGlassCollision(bodyA, bodyB);
        }
        else if (isBird(fixtureB) && isGlass(fixtureA)) {
            handleBirdGlassCollision(bodyB, bodyA);
        }

        if (fixtureA.getBody().getUserData() instanceof Block && fixtureB.getBody().getUserData() instanceof Block ) {
            Block blockA = (Block) fixtureA.getBody().getUserData();
            Block blockB = (Block) fixtureB.getBody().getUserData();
            if (blockA.getBlockBody().getLinearVelocity().y >= (-blockB.getBlockBody().getLinearVelocity().y) + 0.1 || blockB.getBlockBody().getLinearVelocity().y >= (blockA.getBlockBody().getLinearVelocity().y) + 0.1) {

                blockA.addDamage();
                blockB.addDamage();
            }
        }
        if (fixtureA.getBody().getUserData() instanceof Rock && fixtureB.getBody().getUserData() instanceof Rock ) {
            Rock rockA = (Rock) fixtureA.getBody().getUserData();
            Rock rockB = (Rock) fixtureB.getBody().getUserData();
            if (rockA.getRockBody().getLinearVelocity().y >= (-rockB.getRockBody().getLinearVelocity().y) + 0.1 || rockB.getRockBody().getLinearVelocity().y >= (rockA.getRockBody().getLinearVelocity().y) + 0.1) {
                rockA.addDamage();
                rockB.addDamage();
            }
        }
        if (fixtureA.getBody().getUserData() instanceof Glass && fixtureB.getBody().getUserData() instanceof Glass ) {
            Glass glassA = (Glass) fixtureA.getBody().getUserData();
            Glass glassB = (Glass) fixtureB.getBody().getUserData();
            if (glassA.getGlassBody().getLinearVelocity().y >= (-glassB.getGlassBody().getLinearVelocity().y) + 0.1 || glassB.getGlassBody().getLinearVelocity().y >= (glassA.getGlassBody().getLinearVelocity().y) + 0.1) {
                glassA.addDamage();
                glassB.addDamage();
            }
        }


        if (fixtureA.getBody().getUserData() instanceof Pig && fixtureB.getBody().getUserData() instanceof Pig) {

            Pig pigA = (Pig) fixtureA.getBody().getUserData();
            Pig pigB = (Pig) fixtureB.getBody().getUserData();


            pigA.handleCollisionWithPig();
            pigB.handleCollisionWithPig();
        }


        if (isRock(fixtureA) && isBlock(fixtureB)) {
            handleRockBlockCollision(fixtureA.getBody(),fixtureB.getBody());
        }
        else if (isBlock(fixtureA) && isRock(fixtureB)) {
            handleRockBlockCollision(fixtureB.getBody(),fixtureA.getBody());
        }


    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    private boolean isBird(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof BirdInterface;
    }

    public boolean isPig(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof Pig;
    }

    private boolean isBlock(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof Block;
    }

    private boolean isGlass(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof Glass;
    }


    private boolean isRock(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof Rock;
    }




    private void handleBirdBlockCollision(Body bird, Body block) {
        System.out.println("Collision detected between bird and block");

        BirdInterface birdObject = (BirdInterface) bird.getUserData();
        Block blockObject = (Block) block.getUserData();

            blockObject.setBirdtouched(true);
            birdObject.setHealth(birdObject.getHealth() - blockObject.getHealth());
            if (birdObject.getHealth() <= 0) {

            }
        if (birdObject.getHealth() > 0) {
            blockObject.addDamage();
        }
    }

    private void handleBirdRockCollision(Body bird, Body rock) {

        BirdInterface birdObject = (BirdInterface) bird.getUserData();
        Rock rockObject = (Rock) rock.getUserData();
        rockObject.setBirdtouched(true);
        birdObject.setHealth(birdObject.getHealth() - rockObject.getHealth());
        if (birdObject.getHealth() > 0) {
            rockObject.addDamage();
        }
    }

    private void handleBirdGlassCollision(Body bird, Body rock) {
        BirdInterface birdObject = (BirdInterface) bird.getUserData();
        Glass rockObject = (Glass) rock.getUserData();
        rockObject.setBirdtouched(true);
        birdObject.setHealth(birdObject.getHealth() - rockObject.getHealth());
        if (birdObject.getHealth() > 0) {
            rockObject.addDamage();
        }
    }





    private void handlePigBlockCollision(Body pig, Body block) {
        System.out.println("Collision detected between pig and block");

        Pig pigObject = (Pig) pig.getUserData();
        Block blockObject = (Block) block.getUserData();
        System.out.println("block speed: "+blockObject.getBlockBody().getLinearVelocity().y);
        if (Math.abs( blockObject.getBlockBody().getLinearVelocity().y) >= 0.3) {
            System.out.println("Collision detected between bird and block");
            blockObject.setBirdtouched(true);

            pigObject.setHealth(pigObject.getHealth() - blockObject.getHealth());
            pigObject.handleCollisionWithPig();
            blockObject.addDamage();
        }
    }
    private void handlePigRockCollision(Body pig, Body block) {
        System.out.println("Collision detected between pig and block");

        Pig pigObject = (Pig) pig.getUserData();
        Rock blockObject = (Rock) block.getUserData();
        System.out.println("block speed: "+blockObject.getRockBody().getLinearVelocity().y);
        if (Math.abs( blockObject.getRockBody().getLinearVelocity().y) >= 0.3) {
            System.out.println("Collision detected between bird and block");
            blockObject.setBirdtouched(true);

            pigObject.setHealth(pigObject.getHealth() - blockObject.getHealth());
            pigObject.handleCollisionWithPig();
            blockObject.addDamage();
        }
    }

    public void handleBirdPigCollision(Body bird, Body pig) {
        BirdInterface birdObject = (BirdInterface) bird.getUserData();
        Pig pigObject = (Pig) pig.getUserData();
        int pigHealth = pigObject.getHealth();
        int birdHealth = birdObject.getHealth();
        birdObject.setHealth(birdHealth - pigHealth);

        if (birdObject.getHealth() <= 0) {
            birdObject.setCanDestory(true);
        }
        pigObject.handleCollisionWithPig();

    }

    public void handleRockBlockCollision(Body rock, Body block) {
        Rock blockA = (Rock) rock.getUserData();
        Block blockB = (Block) block.getUserData();
        if (blockA.getRockBody().getLinearVelocity().y > 0.1 || blockB.getBlockBody().getLinearVelocity().y >= 0.1) {
            blockA.addDamage();
            blockB.addDamage();
        }
    }

    public void handleRockGlassCollision(Body rock, Body glass) {
        Rock blockA = (Rock) rock.getUserData();
        Glass blockB = (Glass) glass.getUserData();
        if (blockA.getRockBody().getLinearVelocity().y > 0.1 || blockB.getGlassBody().getLinearVelocity().y >= 0.1) {
            blockA.addDamage();
            blockB.addDamage();
        }
    }

    public void handleBlockGlassCollision(Body rock, Body glass) {
        Block blockA = (Block) rock.getUserData();
        Glass blockB = (Glass) glass.getUserData();
        if (blockA.getBlockBody().getLinearVelocity().y > 0.1 || blockB.getGlassBody().getLinearVelocity().y >= 0.1) {
            blockA.addDamage();
            blockB.addDamage();
        }
    }

    private void handlePigGlassCollision(Body pig, Body block) {
        System.out.println("Collision detected between pig and block");

        Pig pigObject = (Pig) pig.getUserData();
        Glass blockObject = (Glass) block.getUserData();

        if (Math.abs( blockObject.getGlassBody().getLinearVelocity().y) >= 0.3) {

            blockObject.setBirdtouched(true);

            pigObject.setHealth(pigObject.getHealth() - blockObject.getHealth());
            pigObject.handleCollisionWithPig();
            blockObject.addDamage();
        }
    }

}
