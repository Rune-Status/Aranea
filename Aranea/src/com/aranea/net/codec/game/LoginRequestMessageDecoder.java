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
package com.aranea.net.codec.game;

import com.aranea.net.ChannelSession;
import com.aranea.net.codec.ChannelMessageDecoder;

public class LoginRequestMessageDecoder implements ChannelMessageDecoder {

    public static final int FRESH_CONNECTION_STATUS = 0x10;
    public static final int EXISTING_CONNECTION_STATUS = 0x12;

    @Override
    public boolean decode(ChannelSession session) {
        int status = session.getBuffer().get() & 0xFF;
        if (status != FRESH_CONNECTION_STATUS && status != EXISTING_CONNECTION_STATUS)
            return false;

        final int length = session.getBuffer().get() & 0xFF;
        
        session.setEncoder(new LoginPayloadMessageEncoder());
        session.setDecoder(new LoginPayloadMessageDecoder(length));
        return true;
    }
}
