#!/bin

## service name
SERVICE_ROOT=$(dirname $(dirname "$PWD"))
SERVICE_DIR=$(cd $(dirname $0); pwd)
SERVICE_NAME=emode-app
JAR_NAME=$SERVICE_NAME\.jar
PID=$SERVICE_NAME\.pid

cd $SERVICE_DIR

start(){
          nohup java -Xms256m -Xmx512m -XX:PermSize=256m -XX:MaxPermSize=4096m -jar $JAR_NAME > $SERVICE_NAME.log 2>&1 &
          echo $! > $SERVICE_DIR/$PID
          echo "=== start $SERVICE_NAME"
}

stop(){
          kill `cat $SERVICE_DIR/$PID`
          rm -rf $SERVICE_DIR/$PID
          echo "=== stop $SERVICE_NAME"

          sleep 5
          P_ID=`ps -ef | grep "$SERVICE_NAME" | grep -v grep | grep -v "$0" | awk '{print $2}'`
          if [ "$P_ID" == "" ]; then
              echo "=== $SERVICE_NAME process not exists or stop success"
          else
              echo "=== $SERVICE_NAME process pid is:$P_ID"
              echo "=== begin kill $SERVICE_NAME process, pid is:$P_ID"
              kill -9 $P_ID
          fi
}

case "$1" in

    start)
        start
        ;;

    stop)
        stop
        ;;

    restart)
        stop
        sleep 2
        start
        echo "=== restart $SERVICE_NAME"
        ;;

    *)
        echo "-------------------------------------"
        echo ""
        echo "项目地址： ${SERVICE_DIR}/${JAR_NAME}"
        echo ""
        echo "你可以使用如下参数进行操作"
        echo "start  -启动当前项目"
        echo "stop  -停止当前项目"
        echo "restart -重启当前项目"
        echo ""
        echo "-------------------------------------"
        ;;

esac
exit 0