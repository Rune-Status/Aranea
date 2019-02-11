/*
 * Copyright (C) 2019 Dylan Vicchiarelli
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aranea.net.codec;

import com.aranea.net.ChannelSession;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

public class LoginHandshakeMessageDecoder implements ChannelMessageDecoder {

    @Override
    public boolean decode(ChannelSession session) {

        /**
         * The name hash. Theorized to help select a proper login server.
         * However, this variable has little to no use in emulation.
         */
        session.getBuffer().get();

        ByteBuffer response = ByteBuffer.allocate(Byte.BYTES + Long.BYTES + Long.BYTES);
        response.putLong(0);
        response.put((byte) 0);
        response.putLong(new SecureRandom().nextLong());
        session.write(response);

        session.setDecoder(new LoginRequestMessageDecoder());
        return true;
    }
}
