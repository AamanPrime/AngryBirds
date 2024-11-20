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

        if (fixtureA.getBody().getUserData() instanceof Block && fixtureB.getBody().getUserData() instanceof Block ) {
            Block blockA = (Block) fixtureA.getBody().getUserData();
            Block blockB = (Block) fixtureB.getBody().getUserData();
            if (blockA.isBirdtouched() || blockB.isBirdtouched()) {
                blockA.setBirdtouched(true);
                blockB.setBirdtouched(true);
                System.out.println(fixtureA.getBody().getPosition() != blockA.getInitialPosition() && fixtureB.getBody().getPosition() != blockB.getInitialPosition());

                blockA.addDamage();
                blockB.addDamage();
            }
        }

        if (fixtureA.getBody().getUserData() instanceof Pig && fixtureB.getBody().getUserData() instanceof Pig) {
            System.out.println("Pig-Pig");
            Pig pigA = (Pig) fixtureA.getBody().getUserData();
            Pig pigB = (Pig) fixtureB.getBody().getUserData();

            // Handle collision between two pigs
            pigA.handleCollisionWithPig();
            pigB.handleCollisionWithPig();
        }

    }

    @Override
    public void endContact(Contact contact) {
        // Optional: Handle when the bird and block stop colliding
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // Optional: Adjust collision details before they are resolved
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // Optional: Access collision response details
    }

    // Helper method to identify if a fixture belongs to a bird
    private boolean isBird(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof BirdInterface;
    }

    // Helper method to identify if a fixture belongs to a block
    private boolean isBlock(Fixture fixture) {
        return fixture.getBody().getUserData() instanceof Block;
    }

    // Handle the collision between a bird and a block
    private void handleBirdBlockCollision(Body bird, Body block) {
        System.out.println("Collision detected between bird and block");

        BirdInterface birdObject = (BirdInterface) bird.getUserData();
        Block blockObject = (Block) block.getUserData();
        blockObject.setBirdtouched(true);
        // Handle the collision effects (e.g., reduce block health or bird speed)
        birdObject.setHealth(birdObject.getHealth() - blockObject.getHealth());
        if (birdObject.getHealth() <= 0) {
            birdObject.setCanDestory(true);
        }

        blockObject.addDamage();
    }
}
