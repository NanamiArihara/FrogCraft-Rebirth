/*
 * Copyright (c) 2015 - 2018 3TUSK, et al.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package frogcraftrebirth.common.lib.util;

import frogcraftrebirth.common.lib.recipes.FrogRecipeInputs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FluidStackFactory {

	private final Map<String, Fluid> fluidLookup = new HashMap<>();

	public FluidStack create(String id, int amount) {
		return new FluidStack(fluidLookup.computeIfAbsent(id, FluidRegistry::getFluid), amount);
	}

	public ItemStack createCell(String id, int cellCount) {
		ItemStack cell = FrogRecipeInputs.UNI_CELL.copy();
		IFluidHandler fluidHandler = FluidUtil.getFluidHandler(cell);
		Objects.requireNonNull(fluidHandler, "Detecting IC2 Universal Fluid Cell that has no IFluidHandler support")
				.fill(this.create(id, Fluid.BUCKET_VOLUME), true);
		cell.setCount(cellCount);
		return cell;
	}
}
