# -*- coding: utf-8 -*-
from tests.lib.utils import *
import pytest


def test_IV_001_002_010(global_test_env, client_consensus_obj):
    node_info = client_consensus_obj.ppos.getValidatorList()
    log.info(node_info)
    nodeid_list = []
    for node in node_info.get("Ret"):
        nodeid_list.append(node.get("NodeId"))
        StakingAddress = node.get("StakingAddress")
        log.info(StakingAddress)
        assert client_consensus_obj.node.web3.toChecksumAddress(StakingAddress) == \
               client_consensus_obj.economic.cfg.DEVELOPER_FOUNDATAION_ADDRESS
    log.info(nodeid_list)
    consensus_node_list = global_test_env.consensus_node_list
    nodeid_list_ = [node.node_id for node in consensus_node_list]
    log.info(nodeid_list_)
    for nodeid in nodeid_list_:
        assert nodeid in nodeid_list


def test_IV_003(global_test_env, client_consensus_obj):
    StakingAddress = global_test_env.cfg.DEVELOPER_FOUNDATAION_ADDRESS
    result = client_consensus_obj.staking.create_staking(0, StakingAddress, StakingAddress)
    log.info("Staking result:{}".format(result))
    assert_code(result,301101)


def test_IV_004(get_generate_account, client_consensus_obj):
    address, _ = get_generate_account
    result = client_consensus_obj.delegate.delegate(0, address)
    log.info(result)
    assert_code(result,301107)


def test_IV_005(global_test_env,client_consensus_obj):
    StakingAddress = global_test_env.cfg.DEVELOPER_FOUNDATAION_ADDRESS
    result = client_consensus_obj.staking.increase_staking(0, StakingAddress)
    assert_code(result,0)


def test_IV_006_007_008(client_consensus_obj, get_generate_account):
    StakingAddress = client_consensus_obj.economic.cfg.DEVELOPER_FOUNDATAION_ADDRESS
    result = client_consensus_obj.staking.withdrew_staking(StakingAddress)
    log.info(result)
    result = client_consensus_obj.ppos.getCandidateInfo(client_consensus_obj.node.node_id)
    log.info(result)
    log.info("进入下3个周期")
    client_consensus_obj.economic.wait_settlement_blocknum(client_consensus_obj.node, number=2)
    msg = client_consensus_obj.ppos.getCandidateInfo(client_consensus_obj.node.node_id)
    log.info(msg)
    assert msg["Code"] == 301204, "预期验证人已退出"
    result = client_consensus_obj.staking.create_staking(0, StakingAddress, StakingAddress)
    assert_code(result,0)
    address, _ = get_generate_account
    result = client_consensus_obj.delegate.delegate(0, address)
    log.info(result)
    assert_code(result,0)
    client_consensus_obj.economic.env.deploy_all()


def test_IV_009(client_consensus_obj, get_generate_account):
    address1, _ = get_generate_account
    StakingAddress = client_consensus_obj.economic.cfg.DEVELOPER_FOUNDATAION_ADDRESS
    result = client_consensus_obj.staking.edit_candidate(StakingAddress, address1)
    log.info(result)
    assert_code(result,0)


def test_P_014_015_019_024(client_noc_list_obj, get_generate_account):
    """
    正常质押,重复质押
    :param client_noconsensus_obj:
    :param get_generate_account:
    :return:
    """
    address, _ = get_generate_account
    log.info("Generate address:{}".format(address))
    result = client_noc_list_obj[0].staking.create_staking(0, address, address)
    log.info(result)
    assert_code(result,0)
    result = client_noc_list_obj[0].staking.create_staking(0, address, address)
    log.info(result)
    assert_code(result,301101)


def test_P_016(client_new_node_obj, get_generate_account):
    """
    未加入链的nodeID质押
    :param client_new_node_obj:
    :param get_generate_account:
    :return:
    """
    illegal_nodeID = "7ee3276fd6b9c7864eb896310b5393324b6db785a2528c00cc28ca8c" \
                     "3f86fc229a86f138b1f1c8e3a942204c03faeb40e3b22ab11b8983c35dc025de42865990"
    address, _ = get_generate_account
    result = client_new_node_obj.staking.create_staking(0, address, address, node_id=illegal_nodeID)
    log.info(result)
    assert_code(result,301003)


def test_P_017(client_new_node_obj, get_generate_account):
    """
    收益地址为激励池地址
    :param client_new_node_obj:
    :param get_generate_account:
    :return:
    """
    INCENTPEPOOL_ADDRESS = client_new_node_obj.economic.cfg.INCENTPEPOOL_ADDRESS
    address, _ = get_generate_account
    result = client_new_node_obj.staking.create_staking(0, INCENTPEPOOL_ADDRESS, address)
    assert_code(result,0)


def test_P_018(client_new_node_obj, get_generate_account):
    """
    收益地址为基金会地址
    :param client_new_node_obj:
    :param get_generate_account:
    :return:
    """
    FOUNDATION_ADDRESS = client_new_node_obj.economic.cfg.FOUNDATION_ADDRESS
    address, _ = get_generate_account
    result = client_new_node_obj.staking.create_staking(0, FOUNDATION_ADDRESS, address)
    assert_code(result,0)


@pytest.mark.P2
def test_P_020_21(client_new_node_obj, get_generate_account):
    """
    自由账户质押金额小于质押门槛,gas不足
    :param client_new_node_obj:
    :param get_generate_account:
    :return:
    """
    address, _ = get_generate_account
    amount = client_new_node_obj.economic.create_staking_limit
    result = client_new_node_obj.staking.create_staking(0, address, address, amount=amount - 1)
    log.info(result)
    assert_code(result,301100)
    cfg = {"gas": 1}
    status = 0
    try:
        result = client_new_node_obj.staking.create_staking(0, address, address, transaction_cfg=cfg)
        log.info(result)
    except:
        status = 1
    assert status == 1


def test_P_025(client_new_node_obj, get_generate_account, client_consensus_obj):
    """
    使用错误的版本签名
    :param client_new_node_obj:
    :param get_generate_account:
    :param client_consensus_obj:
    :return:
    """
    address, _ = get_generate_account
    program_version_sign = client_consensus_obj.node.program_version_sign
    result = client_new_node_obj.staking.create_staking(0, address, address, program_version_sign=program_version_sign)
    log.info(result)
    assert_code(result,301003)


def test_P_029(client_new_node_obj, get_generate_account):
    """
    之前质押过，且候选人已经失效(主动退出)
    锁定期质押
    :param client_new_node_obj:
    :param get_generate_account:
    :return:
    """
    address, _ = get_generate_account
    result = client_new_node_obj.staking.create_staking(0, address, address)
    assert_code(result,0)
    result = client_new_node_obj.staking.withdrew_staking(address)
    log.info(result)
    assert_code(result,0)
    result = client_new_node_obj.staking.create_staking(0, address, address)
    log.info(result)


def test_P_030(client_new_node_obj, get_generate_account):
    """
    锁定期质押
    :param client_new_node_obj:
    :param get_generate_account:
    :return:
    """
    address, _ = get_generate_account
    result = client_new_node_obj.staking.create_staking(0, address, address)
    assert_code(result,0)
    log.info("进入下个周期")
    client_new_node_obj.economic.wait_settlement_blocknum(client_new_node_obj.node)
    result = client_new_node_obj.staking.withdrew_staking(address)
    assert_code(result,0)
    result = client_new_node_obj.staking.create_staking(0, address, address)
    log.info(result)
    assert_code(result,301101)














