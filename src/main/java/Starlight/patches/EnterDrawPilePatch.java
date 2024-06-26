package Starlight.patches;

import Starlight.cards.interfaces.OnEnterDrawPileCard;
import Starlight.util.Wiz;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

public class EnterDrawPilePatch {
    @SpirePatch2(clz = CardGroup.class, method = "addToTop")
    @SpirePatch2(clz = CardGroup.class, method = "addToBottom")
    @SpirePatch2(clz = CardGroup.class, method = "addToRandomSpot")
    public static class CardAddedToGroup {
        @SpirePostfixPatch
        public static void checkCard(CardGroup __instance, AbstractCard c) {
            if (Wiz.adp() != null && __instance.equals(Wiz.adp().drawPile)) {
                if (c instanceof OnEnterDrawPileCard) {
                    ((OnEnterDrawPileCard) c).onEnter();
                }
            }
        }
    }
}
