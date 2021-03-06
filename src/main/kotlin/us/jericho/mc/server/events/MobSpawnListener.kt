package us.jericho.mc.server.events

//import us.jericho.mc.server.EventUtil
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.attribute.Attributable
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeInstance
import org.bukkit.block.Biome
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.inventory.ItemStack
import us.jericho.mc.server.CustomServerPlugin
import us.jericho.mc.server.items.ItemCreator
import us.jericho.mc.server.mobs.unused.ZombieHorseSpawning
import us.jericho.mc.server.worldguard.ZombieHordeFlag


object MobSpawnListener : Listener {

    @EventHandler
    public fun onCreatureSpawn(event: CreatureSpawnEvent) {

        when(event.spawnReason) {
            CreatureSpawnEvent.SpawnReason.NATURAL, CreatureSpawnEvent.SpawnReason.SPAWNER_EGG -> this.naturalSpawn(event);
            CreatureSpawnEvent.SpawnReason.BREEDING -> this.breedingSpawn(event);
        }
    }

    @EventHandler
    public fun ccc() {

    }

    fun naturalSpawn(event: CreatureSpawnEvent) {


        if(isOverwolrdHostile(event.entityType)) {
            if (ZombieHordeFlag.onMobSpawn(event))
                return;
        }

        

        when(event.entityType) {
            EntityType.SKELETON -> { ZombieHorseSpawning.onSkeletonSpawn(event) };
        }

    }

    fun breedingSpawn(event: CreatureSpawnEvent) {
        /**
         * Chance to get single Mooshrooms from breeding cows
         */
        when(event.entityType) {
            EntityType.COW -> {
                var randn = Math.random();
                if(randn < 0.005) {
                    CustomServerPlugin.logger().info("MOOSHROOM FROM BREEDING: " + randn)
                    event.isCancelled = true;

                    var mooshroom: MushroomCow = event.location.world?.spawnEntity(event.location, EntityType.MUSHROOM_COW) as MushroomCow;
                    mooshroom.setBaby();
                }
            }
        }
    }

    fun isOverwolrdHostile(et: EntityType): Boolean {
        return et == EntityType.ZOMBIE || et == EntityType.SKELETON || et == EntityType.CREEPER || et == EntityType.SPIDER;
    }

}