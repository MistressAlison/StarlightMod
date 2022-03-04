package Starlight;

import Starlight.cards.cardvars.Info;
import Starlight.characters.Asphodene;
import Starlight.characters.Eridani;
import Starlight.characters.Starfarers;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import Starlight.cards.cardvars.SecondDamage;
import Starlight.cards.cardvars.SecondMagicNumber;
import Starlight.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class TheStarsAboveMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber {

    public static final String modID = "Starlight";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static final Color METEORITE_PURPLE = CardHelper.getColor(60, 31, 118);

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/bg_attack_space4.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/bg_skill_space4.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/bg_power_space4.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/bg_attack_space4.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/bg_skill_space4.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/bg_power_space4.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/BG.png";

    public static final String ENABLE_CARD_BATTLE_TALK_SETTING = "enableCardBattleTalk";
    public static boolean enableCardBattleTalkEffect = false;

    public static final String CARD_BATTLE_TALK_PROBABILITY_SETTING = "cardTalkProbability";
    public static int cardTalkProbability = 10; //Out of 100

    public static final String ENABLE_DAMAGED_BATTLE_TALK_SETTING = "enableDamagedBattleTalk";
    public static boolean enableDamagedBattleTalkEffect = false;

    public static final String DAMAGED_BATTLE_TALK_PROBABILITY_SETTING = "damagedTalkProbability";
    public static int damagedTalkProbability = 20; //Out of 100

    public static final String ENABLE_PRE_BATTLE_TALK_SETTING = "enablePreBattleTalk";
    public static boolean enablePreBattleTalkEffect = false;

    public static final String PRE_BATTLE_TALK_PROBABILITY_SETTING = "preTalkProbability";
    public static int preTalkProbability = 50; //Out of 100

    public TheStarsAboveMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(Starfarers.Enums.METEORITE_PURPLE_COLOR, METEORITE_PURPLE, METEORITE_PURPLE, METEORITE_PURPLE,
                METEORITE_PURPLE, METEORITE_PURPLE, METEORITE_PURPLE, METEORITE_PURPLE,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        TheStarsAboveMod thismod = new TheStarsAboveMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Asphodene(Starfarers.characterStrings.NAMES[1], Starfarers.Enums.THE_STARFARERS, new Eridani(Starfarers.characterStrings.NAMES[1], Starfarers.Enums.THE_STARFARERS)),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, Starfarers.Enums.THE_STARFARERS);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        BaseMod.addDynamicVariable(new Info());
        new AutoAdd(modID)
                .packageFilter("Starlight.cards")
                .setDefaultSeen(true)
                .cards();
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, modID + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, modID + "Resources/localization/eng/Charstrings.json");

        BaseMod.loadCustomStringsFile(PowerStrings.class, modID + "Resources/localization/eng/Powerstrings.json");

        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/CardModstrings.json");

        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/Chatterstrings.json");

        BaseMod.loadCustomStringsFile(CardStrings.class, modID + "Resources/localization/eng/DamageModstrings.json");

        BaseMod.loadCustomStringsFile(UIStrings.class, modID + "Resources/localization/eng/UIstrings.json");

        //BaseMod.loadCustomStringsFile(MonsterStrings.class, modID + "Resources/localization/eng/Monsterstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receivePostInitialize() {

    }
}
