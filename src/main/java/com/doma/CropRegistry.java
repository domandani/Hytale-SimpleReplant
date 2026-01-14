package com.doma;

import java.util.HashMap;
import java.util.Map;

public class CropRegistry {
    private static final Map<String, CropData> CROPS = new HashMap<>();

    static {
        register("*Plant_Crop_Lettuce_Block_State_Definitions_StageFinal", "Plant_Seeds_Lettuce", "Plant_Crop_Lettuce_Block");
        register("*Plant_Crop_Aubergine_Block_State_Definitions_StageFinal", "Plant_Seeds_Aubergine", "Plant_Crop_Aubergine_Block");
        register("*Plant_Crop_Carrot_Block_State_Definitions_StageFinal", "Plant_Seeds_Carrot", "Plant_Crop_Carrot_Block");
        register("*Plant_Crop_Cauliflower_Block_State_Definitions_StageFinal", "Plant_Seeds_Cauliflower", "Plant_Crop_Cauliflower_Block");
        register("*Plant_Crop_Chilli_Block_State_Definitions_StageFinal", "Plant_Seeds_Chilli", "Plant_Crop_Chilli_Block");
        register("*Plant_Crop_Corn_Block_State_Definitions_StageFinal", "Plant_Seeds_Corn", "Plant_Crop_Corn_Block");
        register("*Plant_Crop_Cotton_Block_State_Definitions_StageFinal", "Plant_Seeds_Cotton", "Plant_Crop_Cotton_Block");
        register("Plant_Crop_Health1", "Plant_Seeds_Health1", "Plant_Crop_Health1_Block");
        register("Plant_Crop_Health2", "Plant_Seeds_Health2", "Plant_Crop_Health2_Block");
        register("Plant_Crop_Health3", "Plant_Seeds_Health3", "Plant_Crop_Health3_Block");
        register("Plant_Crop_Mana1", "Plant_Seeds_Mana1", "Plant_Crop_Mana1_Block");
        register("Plant_Crop_Mana2", "Plant_Seeds_Mana2", "Plant_Crop_Mana2_Block");
        register("Plant_Crop_Mana3", "Plant_Seeds_Mana3", "Plant_Crop_Mana3_Block");
        register("*Plant_Crop_Onion_Block_State_Definitions_StageFinal", "Plant_Seeds_Onion", "Plant_Crop_Onion_Block");
        register("*Plant_Crop_Pumpkin_Block_State_Definitions_StageFinal", "Plant_Seeds_Pumpkin", "Plant_Crop_Pumpkin_Block");
        register("*Plant_Crop_Rice_Block_State_Definitions_StageFinal", "Plant_Seeds_Rice", "Plant_Crop_Rice_Block");
        register("Plant_Crop_Stamina1", "Plant_Seeds_Stamina1", "Plant_Crop_Stamina1_Block");
        register("Plant_Crop_Stamina2", "Plant_Seeds_Stamina2", "Plant_Crop_Stamina2_Block");
        register("Plant_Crop_Stamina3", "Plant_Seeds_Stamina3", "Plant_Crop_Stamina3_Block");
        register("Plant_Flower_Tall_Yellow", "Plant_Seeds_Sunflower", "Plant_Sunflower_Stage_0");
        register("*Plant_Crop_Tomato_Block_State_Definitions_StageFinal", "Plant_Seeds_Tomato", "Plant_Crop_Tomato_Block");
        register("*Plant_Crop_Turnip_Block_State_Definitions_StageFinal", "Plant_Seeds_Turnip", "Plant_Crop_Turnip_Block");
        register("*Plant_Crop_Wheat_Block_State_Definitions_StageFinal", "Plant_Seeds_Wheat", "Plant_Crop_Wheat_Block");
        register("*Plant_Crop_Wild_Grass_Block_State_Definitions_Stage3", "Plant_Seeds_Wild", "Plant_Crop_Wild_Grass_Block");
        register("*Plant_Crop_Potato_Block_State_Definitions_StageFinal", "Plant_Seeds_Potato", "Plant_Crop_Potato_Block");
    }
    private static void register(String finalId, String seedId, String baseId) {
        CROPS.put(finalId, new CropData(finalId, seedId, baseId));
    }

    public static CropData get(String blockId) {
        return CROPS.get(blockId);
    }

    public static boolean isRegistered(String blockId) {
        return CROPS.containsKey(blockId);
    }
}
