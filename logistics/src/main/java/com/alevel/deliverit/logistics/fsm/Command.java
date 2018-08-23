package com.alevel.deliverit.logistics.fsm;


interface Command {
     void accept(Context context);
}
