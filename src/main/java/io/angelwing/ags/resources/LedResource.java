package io.angelwing.ags.resources;

import com.diozero.api.PinInfo;
import com.diozero.devices.LED;
import com.diozero.sbc.DeviceFactoryHelper;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("led")
public class LedResource {

    @POST
    @Path("off")
    public String turnOff(final @QueryParam("gpio") Integer gpio) {
        final PinInfo pinInfo = DeviceFactoryHelper.getNativeDeviceFactory().getBoardInfo().getByGpioNumberOrThrow(gpio);
        try (final LED led = new LED(pinInfo, true, false)) {
            led.off();
        } catch (final Throwable e) {
            return e.getMessage();
        }
        return "turn off led on gpio " + gpio;
    }

    @POST
    @Path("on")
    public String turnOn(final @QueryParam("gpio") Integer gpio) {
        final PinInfo pinInfo = DeviceFactoryHelper.getNativeDeviceFactory().getBoardInfo().getByGpioNumberOrThrow(gpio);
        try (final LED led = new LED(pinInfo, false, true)) {
            led.on();
        } catch (final Throwable e) {
            return e.getMessage();
        }
        return "turn on led on gpio " + gpio;
    }

    @POST
    @Path("blink")
    public String blink(final @QueryParam("gpio") Integer gpio,
                        final @QueryParam("onTime") Integer onTime,
                        final @QueryParam("offTime") Integer offTime,
                        final @QueryParam("iteration") Integer iteration) {
        final PinInfo pinInfo = DeviceFactoryHelper.getNativeDeviceFactory().getBoardInfo().getByGpioNumberOrThrow(gpio);
        try (final LED led = new LED(pinInfo, true, false)) {
            led.blink(onTime, offTime, iteration, false);
        } catch (final Throwable e) {
            return e.getMessage();
        }
        return "blinking led on gpio " + gpio;
    }

}
