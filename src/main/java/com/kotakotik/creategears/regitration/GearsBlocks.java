package com.kotakotik.creategears.regitration;

import com.kotakotik.creategears.blocks.SimpleGearshiftBlock;
import com.kotakotik.creategears.blocks.FullyEncasedBeltBlock;
import com.kotakotik.creategears.blocks.GearBlock;
import com.kotakotik.creategears.util.Registration;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.relays.elementary.BracketedKineticBlockModel;
import com.simibubi.create.content.contraptions.relays.elementary.CogwheelBlockItem;
import com.simibubi.create.content.contraptions.relays.encased.EncasedBeltGenerator;
import com.simibubi.create.foundation.config.StressConfigDefaults;
import com.simibubi.create.foundation.data.*;
import com.simibubi.create.repack.registrate.util.entry.BlockEntry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;

public class GearsBlocks extends Registration {
    public static BlockEntry<GearBlock> GEAR;
    public static BlockEntry<GearBlock> LARGE_GEAR;
    public static BlockEntry<FullyEncasedBeltBlock> FULLY_ENCASED_CHAIN_DRIVE; // oof thats a loong name lmao
    public static BlockEntry<SimpleGearshiftBlock> SIMPLE_GEARSHIFT;

    public GearsBlocks(CreateRegistrate r) {
        super(r);
    }

    @Override
    public void register() {
        GEAR = r.block("gear", (p) -> new GearBlock(false, p))
                .item(CogwheelBlockItem::new).build()
                .blockstate(BlockStateGen.axisBlockProvider(false))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .recipe((ctx, prov) -> {
                    ShapedRecipeBuilder.shapedRecipe(ctx.get(), 8)
                            .patternLine("www")
                            .patternLine("w w")
                            .patternLine("www")
                            .key('w', ItemTags.BUTTONS)
                            .addCriterion("has_cogwheels", prov.hasItem(AllBlocks.COGWHEEL.get()))
                            .build(prov);

                    ctx.get().toCogwheelRecipe(AllBlocks.COGWHEEL.get(), prov);
                    ctx.get().fromCogwheelRecipe(AllBlocks.COGWHEEL.get(), prov);
                })
                .register();

        LARGE_GEAR = r.block("large_gear", (p) -> new GearBlock(true, p))
                .item(CogwheelBlockItem::new).build()
                .blockstate(BlockStateGen.axisBlockProvider(false))
                .onRegister(CreateRegistrate.blockModel(() -> BracketedKineticBlockModel::new))
                .recipe((ctx, prov) -> {
                    ShapedRecipeBuilder.shapedRecipe(ctx.get(), 2)
                            .patternLine("bwb")
                            .patternLine("w w")
                            .patternLine("bwb")
                            .key('w', ItemTags.PLANKS)
                            .key('b', ItemTags.BUTTONS)
                            .addCriterion("has_large_cogwheels", prov.hasItem(AllBlocks.LARGE_COGWHEEL.get()))
                            .build(prov);

                    ctx.get().toCogwheelRecipe(AllBlocks.LARGE_COGWHEEL.get(), prov);
                    ctx.get().fromCogwheelRecipe(AllBlocks.LARGE_COGWHEEL.get(), prov);
                })
                .register();

        FULLY_ENCASED_CHAIN_DRIVE = r.block("fully_encased_chain_drive", FullyEncasedBeltBlock::new)
                .transform(StressConfigDefaults.setNoImpact())
                .item().model((ctx, prov) -> prov.blockItem(FULLY_ENCASED_CHAIN_DRIVE, "/item")).build()
                .blockstate((c, p) ->  // i hope i never have to read this mess
                        (new EncasedBeltGenerator((state, suffix) ->
                                p.models().getExistingFile(p.modLoc("block/" + c.getName() + "/" + c.get().getSuffix(suffix)
                                )))).generate(c, p))
                .recipe((ctx, prov) -> {
                    ctx.get().fullyEncasedChainDriveRecipe(
                            ctx.get().fullyEncasedChainDriveRecipe(prov)
                                .patternLine("s")
                                .patternLine("c")
                                .patternLine("s"),
                            prov, "vertical");

                    ctx.get().fullyEncasedChainDriveRecipe(
                            ctx.get().fullyEncasedChainDriveRecipe(prov)
                                    .patternLine("scs"),
                            prov, "horizontal"
                    );
                })
                .register();

        SIMPLE_GEARSHIFT = r.block("simple_gearshift", SimpleGearshiftBlock::new)
                .initialProperties(SharedProperties::stone)
                .properties(AbstractBlock.Properties::nonOpaque)
                .transform(StressConfigDefaults.setNoImpact())
                .item().model((ctx, prov) -> prov.blockItem(SIMPLE_GEARSHIFT, "/item")).build()
                .blockstate((c, p) -> BlockStateGen.axisBlock(c, p, (b) -> p.models().getExistingFile(p.modLoc("block/simple_gearshift/block"))))
                .recipe((ctx, prov) -> {
                     ctx.get().recipe(
                             ctx.get().recipe(prov)
                                .patternLine("w")
                                .patternLine("c")
                                .patternLine("w"),
                             prov, "vertical");

                     ctx.get().recipe(
                             ctx.get().recipe(prov)
                                .patternLine("wcw"),
                             prov, "horizontal"
                     );
                })
                .register();
    }
}
