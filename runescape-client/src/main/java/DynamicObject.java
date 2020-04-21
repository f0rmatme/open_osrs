import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("cv")
@Implements("DynamicObject")
public class DynamicObject extends Renderable {
	@ObfuscatedName("x")
	@ObfuscatedGetter(
		intValue = -778595855
	)
	@Export("id")
	int id;
	@ObfuscatedName("m")
	@ObfuscatedGetter(
		intValue = 1719688801
	)
	@Export("type")
	int type;
	@ObfuscatedName("k")
	@ObfuscatedGetter(
		intValue = 1645761999
	)
	@Export("orientation")
	int orientation;
	@ObfuscatedName("d")
	@ObfuscatedGetter(
		intValue = -1021480433
	)
	@Export("plane")
	int plane;
	@ObfuscatedName("w")
	@ObfuscatedGetter(
		intValue = 1939594765
	)
	@Export("x")
	int x;
	@ObfuscatedName("v")
	@ObfuscatedGetter(
		intValue = 456415143
	)
	@Export("y")
	int y;
	@ObfuscatedName("q")
	@ObfuscatedSignature(
		signature = "Ljc;"
	)
	@Export("sequenceDefinition")
	SequenceDefinition sequenceDefinition;
	@ObfuscatedName("z")
	@ObfuscatedGetter(
		intValue = -2053050533
	)
	@Export("frame")
	int frame;
	@ObfuscatedName("t")
	@ObfuscatedGetter(
		intValue = 1933430481
	)
	@Export("cycleStart")
	int cycleStart;

	@ObfuscatedSignature(
		signature = "(IIIIIIIZLee;)V"
	)
	DynamicObject(int id, int type, int orientation, int plane, int x, int y, int var7, boolean var8, Renderable var9) {
		this.id = id;
		this.type = type;
		this.orientation = orientation;
		this.plane = plane;
		this.x = x;
		this.y = y;
		if (var7 != -1) {
			this.sequenceDefinition = SpotAnimationDefinition.SequenceDefinition_get(var7);
			this.frame = 0;
			this.cycleStart = Client.cycle - 1;
			if (this.sequenceDefinition.field3525 == 0 && var9 != null && var9 instanceof DynamicObject) {
				DynamicObject var10 = (DynamicObject)var9;
				if (this.sequenceDefinition == var10.sequenceDefinition) {
					this.frame = var10.frame;
					this.cycleStart = var10.cycleStart;
					return;
				}
			}

			if (var8 && this.sequenceDefinition.frameCount != -1) {
				this.frame = (int)(Math.random() * (double)this.sequenceDefinition.frameIds.length);
				this.cycleStart -= (int)(Math.random() * (double)this.sequenceDefinition.frameLengths[this.frame]);
			}
		}

	}

	@ObfuscatedName("t")
	@ObfuscatedSignature(
		signature = "(I)Lel;",
		garbageValue = "480835067"
	)
	@Export("getModel")
	protected final Model getModel() {
		if (this.sequenceDefinition != null) {
			int var1 = Client.cycle - this.cycleStart;
			if (var1 > 100 && this.sequenceDefinition.frameCount > 0) {
				var1 = 100;
			}

			label55: {
				do {
					do {
						if (var1 <= this.sequenceDefinition.frameLengths[this.frame]) {
							break label55;
						}

						var1 -= this.sequenceDefinition.frameLengths[this.frame];
						++this.frame;
					} while(this.frame < this.sequenceDefinition.frameIds.length);

					this.frame -= this.sequenceDefinition.frameCount;
				} while(this.frame >= 0 && this.frame < this.sequenceDefinition.frameIds.length);

				this.sequenceDefinition = null;
			}

			this.cycleStart = Client.cycle - var1;
		}

		ObjectDefinition objectDefinition = WorldMapSection2.getObjectDefinition(this.id);
		if (objectDefinition.transforms != null) {
			objectDefinition = objectDefinition.transform();
		}

		if (objectDefinition == null) {
			return null;
		} else {
			int var2;
			int var3;
			if (this.orientation != 1 && this.orientation != 3) {
				var2 = objectDefinition.sizeX;
				var3 = objectDefinition.sizeY;
			} else {
				var2 = objectDefinition.sizeY;
				var3 = objectDefinition.sizeX;
			}

			int var4 = (var2 >> 1) + this.x;
			int var5 = (var2 + 1 >> 1) + this.x;
			int var6 = (var3 >> 1) + this.y;
			int var7 = (var3 + 1 >> 1) + this.y;
			int[][] var8 = SceneRegion.Tiles_heights[this.plane];
			int var9 = var8[var4][var7] + var8[var5][var6] + var8[var4][var6] + var8[var5][var7] >> 2;
			int var10 = (this.x << 7) + (var2 << 6);
			int var11 = (this.y << 7) + (var3 << 6);
			return objectDefinition.getModelDynamic(this.type, this.orientation, var8, var10, var9, var11, this.sequenceDefinition, this.frame);
		}
	}

	@ObfuscatedName("k")
	@ObfuscatedSignature(
		signature = "(ILlq;Lih;I)V",
		garbageValue = "-389102765"
	)
	static void method2342(int var0, ArchiveDisk var1, Archive var2) {
		byte[] var3 = null;
		synchronized(ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue) {
			for (ArchiveDiskAction var5 = (ArchiveDiskAction)ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.last(); var5 != null; var5 = (ArchiveDiskAction)ArchiveDiskActionHandler.ArchiveDiskActionHandler_requestQueue.previous()) {
				if (var5.key == (long)var0 && var1 == var5.archiveDisk && var5.type == 0) {
					var3 = var5.data;
					break;
				}
			}
		}

		if (var3 != null) {
			var2.load(var1, var0, var3, true);
		} else {
			byte[] var4 = var1.read(var0);
			var2.load(var1, var0, var4, true);
		}
	}
}
