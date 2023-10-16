package vendek.garbagecollectors.events;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class CollectGarbage implements Listener {


    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();

            if (clickedBlock != null && clickedBlock.getType() == Material.YELLOW_WOOL) {
                // Получаем блок над кликнутым блоком.
                Block blockAbove = clickedBlock.getLocation().add(0, 1, 0).getBlock();
                if (blockAbove.getType() != Material.AIR) {
                    return;
                }

                clickedBlock.setType(Material.AIR); // Удалить блок.

                // Сохраняем позицию и на этом месте спавним партиклы.
                Location location = clickedBlock.getLocation().add(0.5, 0.5, 0.5);
                playPoofParticle(location);

                // Воспроизвести звук ломания травы для игрока
                event.getPlayer().playSound(location, Sound.BLOCK_GRASS_BREAK, 1.0f, 1.0f);

                // Создать падающий блок с материалом желтой шерсти на той же позиции, где был клик.
                FallingBlock fallingBlock = location.getWorld().spawnFallingBlock(location, Material.YELLOW_WOOL.createBlockData());
                // Задать скорость движения падающего блока по оси Y
                Vector velocity = new Vector(0, 0.3, 0); // Небольшая скорость вверх для прыжка.
                fallingBlock.setVelocity(velocity);
            }
        }
    }

    private void playPoofParticle(Location location) {
        location.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, location, 4, 0, 0, 0, 0);
    }
}