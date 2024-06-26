package Starlight.cards;

import Starlight.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Starlight.TheStarlightMod.makeID;

public class Defend extends AbstractEasyCard {
    public final static String ID = makeID(Defend.class.getSimpleName());

    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.SELF;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;

    private static final int COST = 1;
    private static final int BLK = 5;
    private static final int UP_BLK = 3;

    public Defend() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = block = BLK;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(UP_BLK);
    }

    @Override
    public String cardArtCopy() {
        return Defend_Red.ID;
    }
}