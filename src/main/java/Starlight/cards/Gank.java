package Starlight.cards;

import Starlight.cards.abstracts.AbstractEasyCard;
import Starlight.cards.interfaces.OnSwapCard;
import Starlight.cards.interfaces.OnTagTeamTriggeredCard;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.FlurryOfBlows;
import com.megacrit.cardcrawl.cards.red.Pummel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Starlight.TheStarlightMod.makeID;

public class Gank extends AbstractEasyCard implements OnSwapCard {
    public final static String ID = makeID(Gank.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 0;
    private static final int DMG = 4;
    private static final int UP_DMG = 2;

    public Gank() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = damage = DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(UP_DMG);
    }

    /*@Override
    public void onTagTriggered(AbstractCard card, AbstractPlayer p, AbstractMonster m) {
        if (p.discardPile.contains(this)) {
            this.addToBot(new DiscardToHandAction(this));
        }
    }*/

    @Override
    public String cardArtCopy() {
        return Pummel.ID;
    }

    @Override
    public void onSwap(boolean toPrim) {
        /*if (Wiz.adp().discardPile.contains(this)) {
            this.addToBot(new DiscardToHandAction(this));
        }*/
    }

    @Override
    public void atTurnStart() {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.atb(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (Wiz.adp().discardPile.contains(Gank.this)) {
                            this.addToTop(new DiscardToHandAction(Gank.this));
                        }
                        this.isDone = true;
                    }
                });
                this.isDone = true;
            }
        });
    }
}