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

import com.aranea.net.codec.game.LoginHandshakeMessageDecoder;
import com.aranea.net.codec.game.LoginHandshakeMessageEncoder;
import com.aranea.net.codec.ondemand.OndemandRequestMessageDecoder;
import com.aranea.net.codec.ondemand.OndemandResponseMessageEncoder;

public class ServiceRequestMessageDecoder implements ChannelMessageDecoder {

    public static final int LOGIN_SERVICE_INDEX = 0xE;
    public static final int ONDEMAND_SERVICE_INDEX = 0xF;

    @Override
    public boolean decode(ChannelSession session) {
        int service = session.getBuffer().get() & 0xFF;
        if (service == LOGIN_SERVICE_INDEX) {
            session.setEncoder(new LoginHandshakeMessageEncoder());
            session.setDecoder(new LoginHandshakeMessageDecoder());
        } else if (service == ONDEMAND_SERVICE_INDEX) {
            session.setEncoder(new OndemandResponseMessageEncoder());
            session.setDecoder(new OndemandRequestMessageDecoder());
        }
        return service == LOGIN_SERVICE_INDEX || service == ONDEMAND_SERVICE_INDEX;
    }
}
