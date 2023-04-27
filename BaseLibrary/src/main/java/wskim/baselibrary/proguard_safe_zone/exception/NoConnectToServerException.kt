package wskim.baselibrary.proguard_safe_zone.exception

import wskim.baselibrary.proguard_safe_zone.exception.base.BaseWebException

class NoConnectToServerException : BaseWebException("인터넷 연결이 필요합니다.")