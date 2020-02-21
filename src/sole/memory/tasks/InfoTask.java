package sole.memory.tasks;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.utils.TextFormat;
import sole.memory.SimpleLand;
import sole.memory.landInfo.LandInfo;

/**
 * Created by SoleMemory
 * on 2017/7/1.
 */
public class InfoTask extends AsyncTask {

    private SimpleLand land;
    public InfoTask(SimpleLand land){
        super();
       this.land = land;
    }
    @Override
    public void onRun() {
        for (Player player: Server.getInstance().getOnlinePlayers().values()) {
            String can = "无操作权限";
            LandInfo info = land.getStepLand(player);
            if (land.isLandWord(player.getLevel().getFolderName())) {
                if (info.getId() == null){
//                    if (SimpleLand.getInstance().isAdmin(player.getName().toLowerCase())){
//                        can = "管理员权限";
//                    }
//                    if(!SimpleLand.getInstance().showMessage.contains(player.getName())){
//                        SimpleLand.getInstance().showMessage.add(player.getName());
//                        player.sendTip(TextFormat.AQUA+"                                                                        >> 名字: 无地皮"
//                                +"\n                                                                           >> ID: 无地皮"
//                                +"\n                                                                      >> 主人: 无"
//                                +"\n                                                                          >> 操作权限: "+TextFormat.RED+can+"\n \n \n \n \n \n \n \n \n\n");
//                    }
                    SimpleLand.getInstance().showMessage.remove(player.getName());
                    continue;
                }
                if (SimpleLand.getInstance().isAdmin(player.getName().toLowerCase())){
                        can = "管理员权限";
                    }
                if (land.isOwner(player, info.getId())) {
                    can = "主人";
                }
                if (land.isGuest(player, info.getId())) {
                    can = "访客";
                }
                if (SimpleLand.getInstance().isAdmin(player.getName().toLowerCase())) {
                    can = "管理员权限";
                }
                if (!SimpleLand.getInstance().showMessage.contains(player.getName())) {
                    SimpleLand.getInstance().showMessage.add(player.getName());
                    player.sendTip(TextFormat.AQUA + "                                                                        >> 名字: " + info.getName()
                            + TextFormat.GOLD + "\n                                                                        >> ID: " + info.getId()
                            + TextFormat.GOLD + "\n                                                                        >> 主人: " + info.getOwner()
                            + "\n                                                                        >> 操作权限: " + TextFormat.GREEN + can + "\n \n \n \n \n \n \n \n \n\n");

                }
            }else{
                SimpleLand.getInstance().showMessage.remove(player.getName());
            }
        }
    }
}
