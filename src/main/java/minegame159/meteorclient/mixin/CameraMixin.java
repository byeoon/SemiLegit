package minegame159.meteorclient.mixin;

import minegame159.meteorclient.modules.render.AntiFog;
import minegame159.meteorclient.modules.render.XRay;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {

    @Inject(method = "clipToSpace", at = @At("HEAD"), cancellable = true)
    private void onClipToSpace(double desiredCameraDistance, CallbackInfoReturnable<Double> info) {
        if (AntiFog.INSTANCE.isActive() || XRay.INSTANCE.isActive()) {
            info.setReturnValue(desiredCameraDistance);
        }
    }
}
