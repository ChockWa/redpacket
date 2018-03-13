package com.hdh.redpacket.game.exception;

import com.hdh.redpacket.core.exception.BizException;

/**
 * 游戏业务相关异常(1201开头)
 */
public class GameException extends BizException {

    /** 游戏未开始 */
    public static final GameException GAME_ISNOT_START = new GameException(12010001,"游戏未开始");

    /** 参数有误 */
    public static final GameException PARAMS_ERROR = new GameException(12010002,"参数有误");

    /** 钻石不足，请购买 */
    public static final GameException DIAMOND_IS_NOT_ENOUGH = new GameException(12010003,"钻石不足，请购买");

    protected GameException(int code, String msg) {
        super(code, msg);
    }
}
