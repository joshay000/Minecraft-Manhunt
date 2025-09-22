package me.manhunt.gui.factories;

import me.manhunt.gui.GuiInventory;
import me.manhunt.gui.GuiItemBase;
import me.manhunt.gui.enums.GuiInventoryType;
import me.manhunt.gui.enums.GuiItemType;
import me.manhunt.gui.inventories.*;
import me.manhunt.gui.items.manhunt.CreateManhuntItem;
import me.manhunt.gui.items.manhunt.create.CreateHunterItem;
import me.manhunt.gui.items.manhunt.create.CreateSpeedrunnerItem;
import me.manhunt.gui.items.manhunt.main.*;
import me.manhunt.gui.items.manhunt.world.*;
import me.manhunt.gui.items.manhunt.world.difficulty.WorldDifficultyEasyItem;
import me.manhunt.gui.items.manhunt.world.difficulty.WorldDifficultyHardItem;
import me.manhunt.gui.items.manhunt.world.difficulty.WorldDifficultyNormalItem;
import me.manhunt.gui.items.manhunt.world.difficulty.WorldDifficultyPeacefulItem;
import me.manhunt.gui.items.manhunt.world.type.WorldTypeAmplifiedItem;
import me.manhunt.gui.items.manhunt.world.type.WorldTypeFlatItem;
import me.manhunt.gui.items.manhunt.world.type.WorldTypeLargeBiomesItem;
import me.manhunt.gui.items.manhunt.world.type.WorldTypeNormalItem;
import me.manhunt.gui.items.pagination.*;
import org.bukkit.entity.Player;

public final class GuiFactory {
    private GuiFactory() {}

    public static GuiInventory makeGui(GuiInventoryType type, Player owner) {
        return switch (type) {
            case MANHUNT_INVENTORY -> new ManhuntGui(owner);
            case MANHUNT_CREATE_INVENTORY -> new ManhuntCreateGui(owner);
            case MANHUNT_MAIN_INVENTORY -> new ManhuntMainGui(owner);
            case MANHUNT_WORLD_SETTINGS_INVENTORY -> new ManhuntWorldSettingsGui(owner);
            case MANHUNT_WORLD_TYPE_INVENTORY -> new ManhuntWorldSettingsTypeGui(owner);
            case MANHUNT_WORLD_DIFFICULTY_INVENTORY -> new ManhuntWorldSettingsDifficultyGui(owner);
            default -> throw new RuntimeException("The inventory type provided is not valid or is not implemented yet.");
        };
    }

    public static GuiItemBase makeItem(GuiItemType type, GuiInventory inventory, Object...args) {
        return switch (type) {
            case PAGINATION_FIRST_PAGE -> new FirstPageItem(inventory);
            case PAGINATION_LAST_PAGE -> new LastPageItem(inventory);
            case PAGINATION_NEXT_PAGE -> new NextPageItem(inventory);
            case PAGINATION_PREVIOUS_PAGE -> new PreviousPageItem(inventory);
            case PAGINATION_CURRENT_PAGE -> new CurrentPageItem(inventory, (int) args[0], (int) args[1]);
            case CREATE_MANHUNT_ITEM -> new CreateManhuntItem(inventory);
            case CREATE_ADD_SPEEDRUNNER_ITEM -> new CreateSpeedrunnerItem(inventory);
            case CREATE_ADD_HUNTER_ITEM -> new CreateHunterItem(inventory);
            case MAIN_ROSTER_ITEM -> new RosterItem(inventory);
            case MAIN_WORLD_ITEM -> new WorldItem(inventory);
            case MAIN_GAME_ITEM -> new GameItem(inventory);
            case MAIN_GOAL_ITEM -> new GoalItem(inventory);
            case MAIN_SPEEDRUNNER_ITEM -> new SpeedrunnerItem(inventory);
            case MAIN_HUNTER_ITEM -> new HunterItem(inventory);
            case MAIN_DISBAND_ITEM -> new DisbandItem(inventory);
            case WORLD_WORLD_TYPE_ITEM -> new WorldTypeItem(inventory);
            case WORLD_GENERATE_STRUCTURES_ITEM -> new GenerateStructuresItem(inventory);
            case WORLD_SEED_ITEM -> new SeedItem(inventory);
            case WORLD_DIFFICULTY_ITEM -> new DifficultyItem(inventory);
            case WORLD_GAME_RULE_ITEM -> new GameRuleItem(inventory);
            case WORLD_TYPE_NORMAL_ITEM -> new WorldTypeNormalItem(inventory);
            case WORLD_TYPE_FLAT_ITEM -> new WorldTypeFlatItem(inventory);
            case WORLD_TYPE_AMPLIFIED_ITEM -> new WorldTypeAmplifiedItem(inventory);
            case WORLD_TYPE_LARGE_BIOMES_ITEM -> new WorldTypeLargeBiomesItem(inventory);
            case WORLD_DIFFICULTY_PEACEFUL_ITEM -> new WorldDifficultyPeacefulItem(inventory);
            case WORLD_DIFFICULTY_EASY_ITEM -> new WorldDifficultyEasyItem(inventory);
            case WORLD_DIFFICULTY_NORMAL_ITEM -> new WorldDifficultyNormalItem(inventory);
            case WORLD_DIFFICULTY_HARD_ITEM -> new WorldDifficultyHardItem(inventory);
            default -> throw new RuntimeException("The item type provided is not valid or is not implemented yet.");
        };
    }
}
