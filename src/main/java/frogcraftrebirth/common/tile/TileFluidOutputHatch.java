/*
 * Copyright (c) 2015 - 2017 3TUSK, et al.
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

package frogcraftrebirth.common.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import frogcraftrebirth.api.tile.ICondenseTowerCore;
import frogcraftrebirth.api.tile.ICondenseTowerOutputHatch;
import frogcraftrebirth.client.gui.GuiFluidOutputHatch;
import frogcraftrebirth.client.gui.GuiTileFrog;
import frogcraftrebirth.common.gui.ContainerFluidOutputHatch;
import frogcraftrebirth.common.gui.ContainerTileFrog;
import frogcraftrebirth.common.lib.FrogFluidTank;
import frogcraftrebirth.common.lib.block.BlockFrogWrenchable;
import frogcraftrebirth.common.lib.capability.FluidHandlerOutputWrapper;
import frogcraftrebirth.common.lib.tile.TileFrog;
import frogcraftrebirth.common.lib.util.ItemUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileFluidOutputHatch extends TileFrog implements ICondenseTowerOutputHatch, IHasGui, ITickable {

	private ICondenseTowerCore mainBlock;
	
	public final ItemStackHandler inv = new ItemStackHandler(2);
	public final FrogFluidTank tank = new FrogFluidTank(8000);

	@Override
	public void update() {
		if (!getWorld().isRemote) {
			if (tank.getFluidAmount() != 0 && !inv.getStackInSlot(0).isEmpty()) {
				if (inv.getStackInSlot(0).hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
					FluidActionResult result = FluidUtil.tryFillContainer(inv.extractItem(0, 1, true), tank, 1000, null, true);
					if (result.isSuccess() && result.result.getCount() > 0) {
						inv.extractItem(0, 1, false);
						ItemStack remainder = inv.insertItem(1, result.result, false);
						if (!remainder.isEmpty() && remainder.getCount() > 0)
							ItemUtil.dropItemStackAsEntityInsanely(getWorld(), getPos(), remainder);
					}
				}
			} 
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		tank.readFromNBT(tag);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		tank.writeToNBT(tag);
		return super.writeToNBT(tag);
	}

	@Override
	public void readPacketData(DataInputStream input) throws IOException {
		tank.readPacketData(input);
	}

	@Override
	public void writePacketData(DataOutputStream output) throws IOException {
		tank.writePacketData(output);
	}
	
	@Override
	public void behave() {
		
	}
	
	@Override
	public ICondenseTowerCore getMainBlock() {
		return mainBlock;
	}

	@Override
	public void setMainBlock(@Nullable ICondenseTowerCore core) {
		this.mainBlock = core;
	}

	@Override
	public boolean canInject(FluidStack stack) {
		return stack != null && (tank.getFluid() == null || this.tank.fill(stack, false) != 0);
	}

	@Override
	public void inject(FluidStack stack, boolean simluated) {
		this.tank.fill(stack, simluated);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			EnumFacing currectFacing = getWorld().getBlockState(getPos()).getValue(BlockFrogWrenchable.FACING_HORIZONTAL);
			return currectFacing == facing;
		}
		
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			EnumFacing currentFacing = getWorld().getBlockState(getPos()).getValue(BlockFrogWrenchable.FACING_HORIZONTAL);
			return currentFacing == facing ? CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(new FluidHandlerOutputWrapper(tank)) : super.getCapability(capability, currentFacing);
		}
		return super.getCapability(capability, facing);
	}

	@Override
	public ContainerTileFrog<? extends TileFrog> getGuiContainer(World world, EntityPlayer player) {
		return new ContainerFluidOutputHatch(player.inventory, this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiTileFrog<? extends TileFrog, ? extends ContainerTileFrog<? extends TileFrog>> getGui(World world, EntityPlayer player) {
		return new GuiFluidOutputHatch(player.inventory, this);
	}

}
