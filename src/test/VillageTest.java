import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class VillageTest {
    @Test
    void shouldAddWorkers() {
        Village v = new Village();
        v.AddWorker("t1","",v::AddFood);
        Assertions.assertEquals(1,v.getWorkers().size());

        v.AddWorker("t2","",v::AddMetal);
        Assertions.assertEquals(2,v.getWorkers().size());

        v.AddWorker("t3","",v::AddWood);
        Assertions.assertEquals(3,v.getWorkers().size());
    }

    @Test
    void workersShouldWork(){
        Village v = new Village();

        v.AddWorker("w1","",v::AddFood);
        assertEquals(v.getFood(), 10);
        v.Day();
        assertEquals(v.getFood(), 10 + 5 - 1);

        v.AddWorker("w2","",v::AddWood);
        assertEquals(v.getWood(), 0);
        v.Day();
        assertEquals(v.getWood(), 1);

        v.AddWorker("w3","",v::AddMetal);
        assertEquals(v.getMetal(), 0);
        v.Day();
        assertEquals(v.getMetal(), 1);
    }

    @Test
    void cannotAddWorkersMoreThanDoubleOfHouses(){
        // first we create a two houses then make sure cannot add more than 10 workers.
        Village v = new Village();
        v.AddWorker("w1","",v::AddFood);
        while (v.getFood() < 100)
            v.Day();
        v.AddWorker("w2","",v::AddWood);
        Utils.repeat(v::Day,10);
        assertEquals(10, v.getWood());

        v.AddWorker("w3","", v::Build);
        v.AddProject("house");
        v.AddProject("house");
        assertEquals( v.getProjects().size(), 2);
        Utils.repeat(v::Day,3);
        assertEquals(v.getBuildings().size(),4);
        Utils.repeat(v::Day,3);
        assertEquals(v.getBuildings().size(),5);
        // asserting all buildings are houses
        assertTrue(v.getBuildings().stream().allMatch(b -> b.getName() == "house"));
        assertEquals( 6,v.getWood());
        while (v.getWorkers().size() != 10)
            v.AddWorker("w","",v::AddFood);
        // adding the 11th worker
        v.AddWorker("w11","", v::AddFood);
        assertEquals(v.getWorkers().size(),10);
    }

    @Test
    void shouldBeGameOverWhenThereAreNoWorkers(){
        Village v = new Village();
        v.Day();
        assertTrue(v.isGameOver());
    }

    @Test
    void hungryWorkersShouldNotWork(){
        Village v = new Village();
        v.AddWorker("w2","",v::AddWood);
        Utils.repeat(v::Day, 10);
        assertEquals(v.getWood(), 10);
        // now our worker should be hungry and therefore should not work
        v.Day();
        assertEquals(v.getWood(), 10);
    }

    @Test
    void shouldNotAddBuildingsICannotAfford(){
        Village v = new Village();
        assertEquals(v.getProjects().size(), 0);
        v.AddProject("woodmill");
        assertEquals(v.getProjects().size(), 0);
    }

    @Test
    void woodmillShouldWork(){
        Village v = new Village();
        // first lets gather enough materials
        v.AddWorker("w","",v::AddFood);
        v.AddWorker("w","",v::AddWood);
        v.AddWorker("w","",v::AddMetal);
        Utils.repeat(v::Day, 10);
        // now let's add a builder and a woodmill project
        v.AddWorker("w","",v::Build);
        v.AddProject("woodmill");
        Utils.repeat(v::Day,3);
        int currentWoods = v.getWood();
        v.Day();
        assertEquals(currentWoods + 1,v.getWood());
        v.Day();
        // now a wood mill is built, so the wood gathering rate should be 3:
        currentWoods = v.getWood();
        v.Day();
        assertEquals(currentWoods + 3,v.getWood());
    }
    @Test
    void quarryShouldWork(){
        Village v = new Village();
        // first lets gather enough materials
        v.AddWorker("w","",v::AddFood);
        v.AddWorker("w","",v::AddWood);
        v.AddWorker("w","",v::AddMetal);
        Utils.repeat(v::Day, 10);
        // now let's add a builder and a quarry project
        v.AddWorker("w","",v::Build);
        v.AddProject("quarry");
        Utils.repeat(v::Day,5);
        int currentMetal = v.getMetal();
        v.Day();
        assertEquals(currentMetal + 1,v.getMetal());
        v.Day();
        // now a quarry is built, so the metal gathering rate should be 3:
        currentMetal = v.getMetal();
        v.Day();
        assertEquals(currentMetal + 3,v.getMetal());
    }

    @Test
    void farmShouldWork(){
        Village v = new Village();
        // first lets gather enough materials
        v.AddWorker("w","",v::AddFood);
        v.AddWorker("w","",v::AddWood);
        v.AddWorker("w","",v::AddMetal);
        Utils.repeat(v::Day, 10);
        // now let's add a builder and a farm project
        v.AddWorker("w","",v::Build);
        v.AddProject("farm");
        Utils.repeat(v::Day,3);
        int currentFood = v.getFood();
        v.Day();
        assertEquals(currentFood + 5 - 4,v.getFood());
        v.Day();
        // now a farm is built, so the wood gathering rate should be 15 (minus 4, because we need to feed 4 workers):
        currentFood = v.getFood();
        v.Day();
        assertEquals(currentFood + 15 - 4 ,v.getFood());
    }

    @Test
    void buildingsShouldTakeExactlyTheDaysTheyNeed(){
        Village v = new Village();
        // first lets gather enough materials
        v.AddWorker("w","",v::AddFood);
        v.AddWorker("w","",v::AddWood);
        v.AddWorker("w","",v::AddMetal);
        Utils.repeat(v::Day, 50);
        // now let's add a builder and a house project
        v.AddWorker("w","",v::Build);
        v.AddProject("house");
        Utils.repeat(v::Day,2);
        assertEquals(3, v.getBuildings().size());
        v.Day();
        assertEquals(4,v.getBuildings().size());

        // now let's add another builder and a house project
        v.AddWorker("w","",v::Build);
        v.AddProject("house");
        v.Day();
        assertEquals(4, v.getBuildings().size());
        v.Day();
        assertEquals(5,v.getBuildings().size());

        // now let's add another builder and a house project (this time the new house should be done in one day)
        v.AddWorker("w","",v::Build);
        v.AddProject("house");
        v.Day();
        assertEquals(6,v.getBuildings().size());
    }

    @Test
    void starvingWorkersShouldDie(){ // sadly ;(
        Village v = new Village();
        v.AddWorker("w2","",v::AddWood);
        Utils.repeat(v::Day, 10);
        assertEquals(v.getWood(), 10);
        // now our worker should be hungry
        Utils.repeat(v::Day, 40);
        assertTrue(v.isGameOver());
    }

    @Test
    void cannotFeedDeadWorkers(){
        Village v = new Village();
        v.AddWorker("w2","",v::AddWood);
        Worker w = v.getWorkers().get(0);
        Utils.repeat(v::Day, 10);
        assertEquals(v.getWood(), 10);
        // now our worker should be hungry
        assertTrue(w.hungry());
        Utils.repeat(v::Day, 40);
        // now our worker should be dead.
        assertFalse(w.alive());
        // our worker should remain hungry after he/she is dead.
        w.Feed();
        assertTrue(w.hungry());
    }

    @Test
    void weShouldBuryTheDead(){
        Village v = new Village();
        v.AddWorker("w2","",v::AddWood);
        Worker w = v.getWorkers().get(0);
        Utils.repeat(v::Day, 10);
        assertEquals(v.getWood(), 10);
        // now our worker should be hungry
        assertTrue(w.hungry());
        Utils.repeat(v::Day, 40);
        // now our worker should be dead.
        assertFalse(w.alive());
        assertEquals(0, v.getWorkers().size());

    }
    @Test
    void CastleShouldTakeExactlyTheDaysItNeeds(){
        Village v = new Village();
        // lets create 2 houses.
        v.AddWorker("w","",v::AddFood);
        v.AddWorker("w","",v::AddWood);
        Utils.repeat(v::Day,10);
        v.AddProject("house");
        v.AddProject("house");
        v.AddWorker("w","",v::Build);
        Utils.repeat(v::Day,6);
        // now we can have 10 workers. let's create these type of workers: 2 food, 2 metal,2 wood, 4 build
        v.AddWorker("w","",v::AddFood);
        v.AddWorker("w","",v::AddMetal);
        v.AddWorker("w","",v::AddMetal);
        v.AddWorker("w","",v::AddWood);

        Utils.repeat(v::Day, 25); // 50=25*2 gathering necessary resources for a castle
        // now let's add our other builders and a castle project

        v.AddWorker("w","",v::Build);
        v.AddWorker("w","",v::Build);
        v.AddWorker("w","",v::Build);
        v.AddProject("castle");

        Utils.repeat(v::Day,12); // with 4 builders castle should be ready in 12.5 days.
        // checking that the castle is built or not?
        assertFalse(v.getBuildings().stream().anyMatch(b -> b.getName() == "castle"));
        assertFalse(v.isGameOver());

        v.Day();
        // checking that the castle is built or not?
        assertTrue(v.getBuildings().stream().anyMatch(b -> b.getName() == "castle"));
        assertTrue(v.isGameOver());

        assertEquals(10+6+25+13,v.getDaysGone());
    }


}